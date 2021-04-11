package com.dfmovies.android.core.data.errorhandler

import com.google.gson.Gson
import com.dfmovies.android.core.data.model.ErrorResponse
import com.dfmovies.android.core.data.model.ConnectionException
import com.dfmovies.android.core.domain.error.ErrorMapper
import com.dfmovies.android.core.util.extension.defaultConnectionNetworkException
import com.dfmovies.android.core.util.extension.defaultNetworkException
import com.dfmovies.android.core.util.extension.logNonFatalException
import com.dfmovies.android.core.util.extension.orEmpty
import retrofit2.HttpException
import java.net.UnknownHostException

object NetworkErrorHandler {

    fun handleError(throwable: Throwable): NetworkException {
        return when (throwable) {
            is HttpException -> {
                handleError(throwable)
            }
            is ConnectionException-> {
                defaultConnectionNetworkException()
            }
            is UnknownHostException ->{
                defaultConnectionNetworkException()
            }
            else -> {
                defaultNetworkException()
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun handleError(httpException: HttpException): NetworkException {
        return try {
            val jsonValue: String? = httpException.response()?.errorBody()?.string()
            val errorResponse: ErrorResponse? =
                Gson().fromJson(jsonValue, ErrorResponse::class.java)
            var errorModel = ErrorMapper.mapOnResponse(errorResponse)
            if (errorModel.message.isEmpty())
                errorModel =
                    errorModel.copy(
                        message = errorModel.errorCode
                    )
            val networkException = NetworkException(
                errorModel.orEmpty(),
                httpException.code()
            )
            logNonFatalException(networkException)
            networkException
        } catch (exception: Exception) {
            logNonFatalException(exception)
            defaultNetworkException(httpExceptionCode = httpException.code())
        }
    }
}