package com.dfmovies.android.core.util.extension

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.dfmovies.android.core.data.errorhandler.NetworkException

fun logNonFatalException(exception: NetworkException) {
    val message =
        "error code : ${exception.error.errorCode} -- message : ${exception.error.message} \n" +
            "httpErrorCode :${exception.code}"
    FirebaseCrashlytics.getInstance().recordException(Exception(message))
}

fun logNonFatalException(exception: Exception) {
    FirebaseCrashlytics.getInstance().recordException(exception)
}

fun logNonFatalException(throwable: Throwable) {
    FirebaseCrashlytics.getInstance().recordException(throwable)
}

fun logNonFatalException(exception: Exception, message: String) {
    FirebaseCrashlytics.getInstance()
        .recordException(Exception(message.plus("\n").plus(exception.message)))
}