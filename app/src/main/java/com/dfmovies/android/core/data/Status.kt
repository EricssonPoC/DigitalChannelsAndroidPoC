package com.dfmovies.android.core.data

import com.erkutaras.statelayout.StateLayout

sealed class Status {

    object Content : Status()

    object Loading : Status()

    object LoadingWithContent : Status()

    object Empty : Status()

    data class Error(val exception: Throwable) : Status()

    fun getStateInfo(
        onEmpty: () -> StateLayout.StateInfo = { StateLayout.provideEmptyStateInfo() },
        onError: (Throwable) -> StateLayout.StateInfo
    ): StateLayout.StateInfo = when (this) {
        is Content -> StateLayout.provideContentStateInfo()
        is Loading -> StateLayout.provideLoadingStateInfo()
        is LoadingWithContent -> StateLayout.provideLoadingWithContentStateInfo()
        is Error -> onError(exception)
        is Empty -> onEmpty()
    }
}
