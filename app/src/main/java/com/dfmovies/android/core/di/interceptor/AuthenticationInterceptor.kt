package com.dfmovies.android.core.di.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class AuthenticationInterceptor @Inject constructor(
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()

        val builder = originRequest.newBuilder()

        return chain.proceed(builder.build())
    }
}