package com.dfmovies.android.main.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dfmovies.android.core.data.Status
import com.dfmovies.android.core.viewmodel.ReactiveBaseViewModel
import com.dfmovies.android.main.domain.MoviesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragmentViewModel @Inject constructor(
        private val moviesUseCase: MoviesUseCase
) : ReactiveBaseViewModel() {


    private val pageStatusViewStateLiveData: MutableLiveData<FavoritePageStatusViewState> =
            MutableLiveData()

    private val pageViewStateLiveData: MutableLiveData<FavoritePageViewState> =
            MutableLiveData()

    fun getPageStatusViewStateLiveData(): LiveData<FavoritePageStatusViewState> =
            pageStatusViewStateLiveData

    fun getPageViewStateLiveData(): LiveData<FavoritePageViewState> =
            pageViewStateLiveData

    private fun setPageStatusViewStateLiveData(pageViewState: FavoritePageStatusViewState) {
        pageStatusViewStateLiveData.value = (pageViewState)
    }

    private fun setPageViewStateLiveData(pageViewState: FavoritePageViewState) {
        pageViewStateLiveData.value = (pageViewState)
    }

    private fun showLoadingForPage() {
        pageStatusViewStateLiveData.value?.let { viewState ->
            setPageStatusViewStateLiveData(viewState.copy(status = Status.LoadingWithContent))
        }
                ?: setPageStatusViewStateLiveData(FavoritePageStatusViewState(status = Status.LoadingWithContent))
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

    fun getFavoriteMovies() {
        viewModelScope.launch(favoriteExceptionHandler) {
            showLoadingForPage()
            val movies = moviesUseCase.getFavoriteMoviesFromDb()
            if (movies.isEmpty()) {
                setPageEmpty()
            } else {
                setPageViewStateLiveData(FavoritePageViewState(movies = movies))
                dismissLoadingForPage()
            }
        }
    }

    private val favoriteExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        dismissLoadingForPage()
        val networkException = getNetworkException(throwable)
        setErrorMessageLiveData(networkException)
    }
}