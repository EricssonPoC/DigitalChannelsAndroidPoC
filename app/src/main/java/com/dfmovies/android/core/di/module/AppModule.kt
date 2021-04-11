package com.dfmovies.android.core.di.module

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.dfmovies.android.DfMoviesApplication
import com.dfmovies.android.core.di.qualifier.ApplicationContext
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: DfMoviesApplication): Application

    @SuppressLint("ModuleCompanionObjects")
    @Module
    companion object {
        
        @Provides
        @Singleton
        @ApplicationContext
        fun provideContext(application: Application): Context {
            return application.applicationContext
        }
    }
}