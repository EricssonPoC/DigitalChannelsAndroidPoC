package com.dfmovies.android.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dfmovies.android.authentication.ui.login.LoginViewModel
import com.dfmovies.android.core.viewmodel.ViewModelFactory
import com.dfmovies.android.core.viewmodel.ViewModelKey
import com.dfmovies.android.main.ui.detail.MovieDetailViewModel
import com.dfmovies.android.main.ui.discover.DiscoverMovieFragmentViewModel
import com.dfmovies.android.main.ui.favorite.FavoriteFragmentViewModel
import com.dfmovies.android.main.ui.search.SearchFragmentViewModel
import com.dfmovies.android.main.ui.watchlist.WatchlistFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverMovieFragmentViewModel::class)
    abstract fun provideDiscoverMovieFragmentViewModel(viewModel: DiscoverMovieFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentViewModel::class)
    abstract fun provideSearchFragmentViewModel(viewModel: SearchFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WatchlistFragmentViewModel::class)
    abstract fun provideWatchlistFragmentViewModel(viewModel: WatchlistFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteFragmentViewModel::class)
    abstract fun provideFavoriteFragmentViewModel(viewModel: FavoriteFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun provideMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel


}
