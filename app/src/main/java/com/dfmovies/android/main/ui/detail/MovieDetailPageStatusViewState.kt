package com.dfmovies.android.main.ui.detail

import com.dfmovies.android.core.data.Status
import com.erkutaras.statelayout.StateLayout

data class MovieDetailPageStatusViewState(
    val status: Status = Status.LoadingWithContent
) {

    fun getStateInfo(): StateLayout.StateInfo =
        status.getStateInfo(
            onEmpty = { StateLayout.provideContentStateInfo() },
            onError = { StateLayout.provideContentStateInfo() }
        )
}