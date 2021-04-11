package com.dfmovies.android.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dfmovies.android.core.data.errorhandler.NetworkErrorHandler
import com.dfmovies.android.core.data.errorhandler.NetworkException
import com.dfmovies.android.core.domain.model.RError
import com.dfmovies.android.core.util.extension.SingleLiveEvent
import com.dfmovies.android.core.util.extension.defaultNetworkException
import kotlinx.coroutines.CoroutineExceptionHandler

open class ReactiveBaseViewModel : ViewModel() {

    private val errorMessageLiveData = SingleLiveEvent<RError>()

    private val loadingLiveData = SingleLiveEvent<Boolean>()

    fun getErrorMessageLiveData(): LiveData<RError> = errorMessageLiveData

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData

    fun setErrorMessageLiveData(exception: Throwable) {
        when (exception) {
            is NetworkException -> {
                    errorMessageLiveData.value = exception.error
            }
            else -> errorMessageLiveData.value = defaultNetworkException().error
        }
    }


    fun getNetworkExceptionError(throwable: Throwable): RError? {
        return (throwable as? NetworkException)?.error
    }

    fun getNetworkException(throwable: Throwable): NetworkException {
        return NetworkErrorHandler.handleError(throwable)
    }

    fun showLoadingLiveData() {
        loadingLiveData.value = true
    }

    fun dismissLoadingLiveData() {
        loadingLiveData.value = false
    }

    fun showCoroutineLoadingLiveData() {
        loadingLiveData.postValue(true)
    }

    fun dismissCoroutineLoadingLiveData() {
        loadingLiveData.postValue(false)
    }

    internal val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        dismissCoroutineLoadingLiveData()
        val handledThrowable = NetworkErrorHandler.handleError(throwable)
        setErrorMessageLiveData(handledThrowable)
    }
}
