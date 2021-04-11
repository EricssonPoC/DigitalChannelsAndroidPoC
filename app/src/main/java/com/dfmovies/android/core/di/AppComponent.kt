package com.dfmovies.android.core.di

import com.dfmovies.android.DfMoviesApplication
import com.dfmovies.android.core.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        LocalDataModule::class,
        DataModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<DfMoviesApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<DfMoviesApplication>
}