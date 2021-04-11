package com.dfmovies.android.core.di.module

import com.dfmovies.android.main.di.MovieDataModule
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        MovieDataModule::class
    ]
)
class DataModule