package com.dfmovies.android.main.ui.search

import com.dfmovies.android.core.data.Status
import com.erkutaras.statelayout.StateLayout

data class SearchPageStatusViewState(
    val status: Status = Status.Content,
    val isDataInitiallyEmpty: Boolean = false
) {

    fun getStateInfo(): StateLayout.StateInfo =
        status.getStateInfo(
            onEmpty = { StateLayout.provideContentStateInfo() },
            onError = { StateLayout.provideContentStateInfo() }
        )
}