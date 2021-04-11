package com.dfmovies.android.core.di.module

import com.dfmovies.android.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(
    includes = [
        InterceptorSetModule::class
    ]
)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().disableHtmlEscaping().create()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit =
        buildRetrofit(okHttpClient, gson)


    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkInterceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient =
        buildOkHttpClient(networkInterceptors)

    private fun buildOkHttpClient(
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient =
        OkHttpClient.Builder().also { builder ->
            with(builder) {
                interceptors().addAll(interceptors)
                connectTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
                writeTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
                readTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
            }
        }.build()

    private fun buildRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.REST_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    companion object {
        const val TIME_OUT_DURATION: Long = 30
    }
}
