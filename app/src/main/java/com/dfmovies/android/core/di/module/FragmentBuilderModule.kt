package com.dfmovies.android.core.di.module

import com.dfmovies.android.authentication.ui.login.LoginFragment
import com.dfmovies.android.main.di.*
import com.dfmovies.android.main.ui.detail.MovieDetailFragment
import com.dfmovies.android.main.ui.discover.DiscoverMovieFragment
import com.dfmovies.android.main.ui.favorite.FavoriteFragment
import com.dfmovies.android.main.ui.search.SearchFragment
import com.dfmovies.android.main.ui.watchlist.WatchlistFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindsLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [DiscoverFragmentModule::class])
    abstract fun bindsDiscoverMovieFragment(): DiscoverMovieFragment

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun bindsSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = [WatchlistFragmentModule::class])
    abstract fun bindsWatchlistFragment(): WatchlistFragment

    @ContributesAndroidInjector(modules = [FavoriteFragmentModule::class])
    abstract fun bindsFavoriteFragment(): FavoriteFragment

    @ContributesAndroidInjector(modules = [MovieDetailFragmentModule::class])
    abstract fun bindsMovieDetailFragment(): MovieDetailFragment

}