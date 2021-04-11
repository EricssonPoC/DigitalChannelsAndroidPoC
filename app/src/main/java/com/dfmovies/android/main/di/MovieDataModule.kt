package com.dfmovies.android.main.di

import com.dfmovies.android.main.data.source.remote.MovieService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
class MovieDataModule {

    @Provides
    @Singleton
    fun provideAccountService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}