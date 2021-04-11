package com.dfmovies.android.main.ui.discover

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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverMovieFragmentViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : ReactiveBaseViewModel() {

    private val discoverFilterMap = mutableMapOf<String, String>()

    private val pageStatusViewStateLiveData: MutableLiveData<DiscoverMoviePageStatusViewState> =
        MutableLiveData()

    private val moviesLiveData: MutableLiveData<PagingData<Movie>> =
        MutableLiveData()

    private val initialPageLiveData: MutableLiveData<Boolean> = SingleLiveEvent()

    fun getPageStatusViewStateLiveData(): LiveData<DiscoverMoviePageStatusViewState> =
        pageStatusViewStateLiveData

    fun getMoviesLiveData(): LiveData<PagingData<Movie>> =
        moviesLiveData

    private fun setPageViewStateLiveData(pageViewState: DiscoverMoviePageStatusViewState) {
        pageStatusViewStateLiveData.value = (pageViewState)
    }

    private fun setMoviesLiveData(data: PagingData<Movie>) {
        moviesLiveData.value = (data)
    }

    private fun showLoadingForPage() {
        pageStatusViewStateLiveData.value?.let { viewState ->
            setPageViewStateLiveData(viewState.copy(status = Status.LoadingWithContent))
        }
            ?: setPageViewStateLiveData(DiscoverMoviePageStatusViewState(status = Status.LoadingWithContent))
    }

    private fun dismissLoadingForPage() {
        pageStatusViewStateLiveData.value?.let { viewState ->
            setPageViewStateLiveData(viewState.copy(status = Status.Content))
        }
    }

    fun setPageState(loadState: CombinedLoadStates) {
        if (loadState.refresh is LoadState.Loading)
            showLoadingForPage()
        else {
            // Hide ProgressBar
            dismissLoadingForPage()
            // If we have an error, show a toast
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                val networkException = getNetworkException(it.error)
                setErrorMessageLiveData(networkException)
            }
        }
    }

    fun loadMovies() {
        viewModelScope.launch(discoverExceptionHandler) {
            discoverMovieData.collectLatest { setMoviesLiveData(it) }
        }
    }

    private val discoverMovieData =
        moviesUseCase.discoverMovie(filterMap = discoverFilterMap).cachedIn(viewModelScope)


    private val discoverExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        dismissLoadingForPage()
        val networkException = getNetworkException(throwable)
        setErrorMessageLiveData(networkException)
    }
}