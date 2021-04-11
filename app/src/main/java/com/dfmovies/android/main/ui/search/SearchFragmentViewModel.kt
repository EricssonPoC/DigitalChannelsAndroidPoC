package com.dfmovies.android.main.ui.search

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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
        private val moviesUseCase: MoviesUseCase
) : ReactiveBaseViewModel() {

    private val searchFilterMap = mutableMapOf<String, String>()

    private val pageStatusViewStateLiveData: MutableLiveData<SearchPageStatusViewState> =
            MutableLiveData()

    private val pageViewStateLiveData: MutableLiveData<SearchPageViewState> =
            MutableLiveData()

    private val moviesLiveData: MutableLiveData<PagingData<Movie>> =
            MutableLiveData()

    private val initialPageLiveData: MutableLiveData<Boolean> = SingleLiveEvent()

    fun getPageStatusViewStateLiveData(): LiveData<SearchPageStatusViewState> =
            pageStatusViewStateLiveData

    fun getPageViewStateLiveData(): LiveData<SearchPageViewState> =
            pageViewStateLiveData

    fun getMoviesLiveData(): LiveData<PagingData<Movie>> =
            moviesLiveData

    fun getInitialPageLiveData(): LiveData<Boolean> = initialPageLiveData

    private fun setPageStatusViewStateLiveData(pageViewState: SearchPageStatusViewState) {
        pageStatusViewStateLiveData.value = (pageViewState)
    }

    private fun setPageViewStateLiveData(pageViewState: SearchPageViewState) {
        pageViewStateLiveData.value = (pageViewState)
    }

    private fun setMoviesLiveData(data: PagingData<Movie>) {
        moviesLiveData.value = (data)
    }

    private fun showLoadingForPage() {
        pageStatusViewStateLiveData.value?.let { viewState ->
            setPageStatusViewStateLiveData(viewState.copy(status = Status.LoadingWithContent))
        }
                ?: setPageStatusViewStateLiveData(SearchPageStatusViewState(status = Status.LoadingWithContent))
    }

    private fun dismissLoadingForPage() {
        pageStatusViewStateLiveData.value?.let { viewState ->
            setPageStatusViewStateLiveData(viewState.copy(status = Status.Content))
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

    fun loadInitialData() {
        viewModelScope.launch(filterDataExceptionHandler) {
            setPageStatusViewStateLiveData(SearchPageStatusViewState())
            setPageViewStateLiveData(SearchPageViewState())
            setSearchQuery(INITIAL_SEARCH_QUERY)
            searchMovieData.collectLatest { setMoviesLiveData(it) }

        }
    }

    private val searchMovieData =
            moviesUseCase.searchMovie(filterMap = searchFilterMap).cachedIn(viewModelScope)


    fun setSearchQuery(searchQuery: String) {
        if (searchQuery.isNotEmpty()) {
            searchFilterMap[MovieFilterDefinition.QUERY] = searchQuery
        } else {
            searchFilterMap[MovieFilterDefinition.QUERY] = INITIAL_SEARCH_QUERY
        }
    }

    private val filterDataExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        dismissLoadingForPage()
        val networkException = getNetworkException(throwable)
        setErrorMessageLiveData(networkException)
    }

    companion object {
        private const val INITIAL_SEARCH_QUERY = "A"
    }
}