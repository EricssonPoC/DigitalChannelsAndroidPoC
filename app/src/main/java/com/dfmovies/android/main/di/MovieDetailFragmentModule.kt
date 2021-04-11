package com.dfmovies.android.main.di

import com.dfmovies.android.main.ui.detail.MovieDetailFragment
import com.dfmovies.android.main.ui.detail.MovieDetailFragmentArgument
import dagger.Module
import dagger.Provides

@Module
abstract class MovieDetailFragmentModule {

    companion object {
        @Provides
        fun providesMovieDetailArguments(fragment: MovieDetailFragment): MovieDetailFragmentArgument {
            return fragment.arguments?.getParcelable(MovieDetailFragment.MOVIE_ARGS)
                ?: throw IllegalArgumentException("Movie Detail screen has to include with Arguments")
        }
    }

}