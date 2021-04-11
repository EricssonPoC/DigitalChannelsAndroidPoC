package com.dfmovies.android.main.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dfmovies.android.core.data.Status
import com.dfmovies.android.core.viewmodel.ReactiveBaseViewModel
import com.dfmovies.android.main.domain.MoviesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class WatchlistFragmentViewModel @Inject constructor(
        private val moviesUseCase: MoviesUseCase
) : ReactiveBaseViewModel() {


    private val pageStatusViewStateLiveData: MutableLiveData<WatchlistPageStatusViewState> =
            MutableLiveData()

    private val pageViewStateLiveData: MutableLiveData<WatchlistPageViewState> =
            MutableLiveData()

    fun getPageStatusViewStateLiveData(): LiveData<WatchlistPageStatusViewState> =
            pageStatusViewStateLiveData

    fun getPageViewStateLiveData(): LiveData<WatchlistPageViewState> =
            pageViewStateLiveData

    private fun setPageStatusViewStateLiveData(pageViewState: WatchlistPageStatusViewState) {
        pageStatusViewStateLiveData.value = (pageViewState)
    }

    private fun setPageViewStateLiveData(pageViewState: WatchlistPageViewState) {
        pageViewStateLiveData.value = (pageViewState)
    }

    private fun showLoadingForPage() {
        pageStatusViewStateLiveData.value?.let { viewState ->
            setPageStatusViewStateLiveData(viewState.copy(status = Status.LoadingWithContent))
        }
                ?: setPageStatusViewStateLiveData(WatchlistPageStatusViewState(status = Status.LoadingWithContent))
    }

    private fun dismissLoadingForPage() {
        pageStatusViewStateLiveData.value?.let { viewState ->
            setPageStatusViewStateLiveData(viewState.copy(status = Status.Content))
        }
    }

    private fun setPageEmpty() {
        pageStatusViewStateLiveData.value?.let { viewState ->
            setPageStatusViewStateLiveData(viewState.copy(status = Status.Empty))
        }
    }

    fun getWatchlistMovies() {
        viewModelScope.launch(watchlistExceptionHandler) {
            showLoadingForPage()
            val movies = moviesUseCase.getWatchlistMoviesFromDb()
            if (movies.isEmpty()) {
                setPageEmpty()
            } else {
                setPageViewStateLiveData(WatchlistPageViewState(movies = movies))
                dismissLoadingForPage()
            }
        }
    }

    private val watchlistExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        dismissLoadingForPage()
        val networkException = getNetworkException(throwable)
        setErrorMessageLiveData(networkException)
    }
}