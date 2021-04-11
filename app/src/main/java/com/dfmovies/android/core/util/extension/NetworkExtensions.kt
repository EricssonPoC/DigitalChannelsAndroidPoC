package com.dfmovies.android.core.util.extension

import com.dfmovies.android.R
import com.dfmovies.android.DfMoviesApplication
import com.dfmovies.android.core.data.errorhandler.NetworkException
import com.dfmovies.android.core.domain.model.RError


fun RError?.orEmpty() = this ?: RError(
    errorCode = "",
    message = ""
)

fun defaultNetworkException(
    message: String = DfMoviesApplication.applicationContext.getString(R.string.generic_error_message),
    httpExceptionCode: Int = -1
): NetworkException {
    return NetworkException(
        RError(
            errorCode = "",
            message = message
        ),
        httpExceptionCode
    )
}

fun defaultConnectionNetworkException(
    message: String = DfMoviesApplication.applicationContext.getString(R.string.connection_error_message),
    httpExceptionCode: Int = -1
): NetworkException {
    return NetworkException(
        RError(
            errorCode ="CLIENT_CONNECTION_ERROR",
            message = message,
        ),
        httpExceptionCode
    )
}

