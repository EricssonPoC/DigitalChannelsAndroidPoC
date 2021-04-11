package com.dfmovies.android.main.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dfmovies.android.core.data.Status
import com.dfmovies.android.core.util.extension.SingleLiveEvent
import com.dfmovies.android.core.viewmodel.ReactiveBaseViewModel
import com.dfmovies.android.main.domain.MoviesUseCase
import com.dfmovies.android.main.domain.model.Movie
import com.dfmovies.android.main.domain.model.MovieFilterDefinition
import com.dfmovies.android.main.ui.search.SearchPageStatusViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
        private val moviesUseCase: MoviesUseCase
) : ReactiveBaseViewModel() {


    private val pageStatusViewStateLiveData: MutableLiveData<MovieDetailPageStatusViewState> =
            MutableLiveData()

    private val pageViewStateLiveData: MutableLiveData<MovieDetailPageViewState> =
            MutableLiveData()

    fun getPageStatusViewStateLiveData(): LiveData<MovieDetailPageStatusViewState> =
            pageStatusViewStateLiveData

    fun getPageViewStateLiveData(): LiveData<MovieDetailPageViewState> =
            pageViewStateLiveData

    private fun setPageStatusViewStateLiveData(pageViewState: MovieDetailPageStatusViewState) {
        pageStatusViewStateLiveData.value = (pageViewState)
    }

    private fun setPageViewStateLiveData(pageViewState: MovieDetailPageViewState) {
        pageViewStateLiveData.value = (pageViewState)
    }

    private fun showLoadingForPage() {
        pageStatusViewStateLiveData.value?.let { viewState ->
            setPageStatusViewStateLiveData(viewState.copy(status = Status.LoadingWithContent))
        }
                ?: setPageStatusViewStateLiveData(MovieDetailPageStatusViewState(status = Status.LoadingWithContent))
    }

    private fun dismissLoadingForPage() {
        pageStatusViewStateLiveData.value?.let { viewState ->
            setPageStatusViewStateLiveData(viewState.copy(status = Status.Content))
        }
    }

    fun initData(movie: Movie) {
        viewModelScope.launch(detailExceptionHandler) {
            showLoadingForPage()
            moviesUseCase.getMovie(movie.id)?.let {
                setPageViewStateLiveData(MovieDetailPageViewState(it))
            } ?: setPageViewStateLiveData(MovieDetailPageViewState(movie))
            dismissLoadingForPage()
        }
    }

    fun createOrUpdateForFavorite() {
        viewModelScope.launch(detailExceptionHandler) {
            showLoadingForPage()
            pageViewStateLiveData.value?.let { viewState ->
                val movie = viewState.movie
                when {
                    movie.isNotInDb() -> {
                        val newMovie = movie.copy(isInFavorite = true)
                        moviesUseCase.addMovie(newMovie)
                        updatePageViewState(newMovie)
                    }
                    movie.isInFavorite -> {
                        val newMovie = movie.copy(isInFavorite = false)
                        moviesUseCase.updateMovie(newMovie)
                        updatePageViewState(newMovie)
                    }
                    movie.isInFavorite.not() -> {
                        val newMovie = movie.copy(isInFavorite = true)
                        moviesUseCase.updateMovie(newMovie)
                        updatePageViewState(newMovie)
                    }
                }
                dismissLoadingForPage()
            }
        }
    }

    fun createOrUpdateForWatchlist() {
        viewModelScope.launch(detailExceptionHandler) {
            showLoadingForPage()
            pageViewStateLiveData.value?.let { viewState ->
                val movie = viewState.movie
                when {
                    movie.isNotInDb() -> {
                        val newMovie = movie.copy(isInWatchList = true)
                        moviesUseCase.addMovie(newMovie)
                        updatePageViewState(newMovie)
                    }
                    movie.isInWatchList -> {
                        val newMovie = movie.copy(isInWatchList = false)
                        moviesUseCase.updateMovie(newMovie)
                        updatePageViewState(newMovie)
                    }
                    movie.isInWatchList.not() -> {
                        val newMovie = movie.copy(isInWatchList = true)
                        moviesUseCase.updateMovie(newMovie)
                        updatePageViewState(newMovie)
                    }
                }
                dismissLoadingForPage()
            }
        }
    }

    private fun updatePageViewState(movie: Movie) {
        pageViewStateLiveData.value?.let { viewState ->
            setPageViewStateLiveData(viewState.copy(movie = movie))
        }
    }

    private fun Movie.isNotInDb(): Boolean {
        return isInWatchList.not() && isInFavorite.not()
    }

    private val detailExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        dismissLoadingForPage()
        val networkException = getNetworkException(throwable)
        setErrorMessageLiveData(networkException)
    }
}