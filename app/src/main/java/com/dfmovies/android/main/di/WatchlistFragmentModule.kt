package com.dfmovies.android.main.di

import com.dfmovies.android.main.ui.adapter.MoviesAdapter
import dagger.Module
import dagger.Provides

@Module
class WatchlistFragmentModule {

    @Provides
    fun provideMoviesAdapter(): MoviesAdapter {
        return MoviesAdapter()
    }
}