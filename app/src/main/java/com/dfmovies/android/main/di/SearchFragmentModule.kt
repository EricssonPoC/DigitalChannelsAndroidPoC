package com.dfmovies.android.main.di

import com.dfmovies.android.main.ui.adapter.SearchMoviesPagingAdapter
import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule {

    @Provides
    fun provideSearchMoviePagingAdapter(): SearchMoviesPagingAdapter {
        return SearchMoviesPagingAdapter()
    }
}