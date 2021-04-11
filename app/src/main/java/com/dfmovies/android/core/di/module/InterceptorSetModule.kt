package com.dfmovies.android.core.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dfmovies.android.core.di.interceptor.AuthenticationInterceptor
import com.dfmovies.android.core.di.interceptor.HeaderInterceptor
import com.dfmovies.android.core.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor

@Module
object InterceptorSetModule {

    @IntoSet
    @Provides
    fun provideAuthenticationInterceptor(): Interceptor {
        return AuthenticationInterceptor()
    }

    @IntoSet
    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return HeaderInterceptor()
    }

    @IntoSet
    @Provides
    fun provideChuckInterceptor(@ApplicationContext context: Context): Interceptor {
        return ChuckerInterceptor.Builder(context).build()
    }

}