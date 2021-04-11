package com.dfmovies.android.main.ui.discover

import com.dfmovies.android.core.data.Status
import com.erkutaras.statelayout.StateLayout

data class DiscoverMoviePageStatusViewState(
    val status: Status = Status.LoadingWithContent,
    val isDataInitiallyEmpty: Boolean = false
) {

    fun getStateInfo(): StateLayout.StateInfo =
        status.getStateInfo(
            onEmpty = { StateLayout.provideContentStateInfo() },
            onError = { StateLayout.provideContentStateInfo() }
        )
}