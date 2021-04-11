package com.dfmovies.android.main.ui.favorite

import android.content.Context
import com.dfmovies.android.R
import com.dfmovies.android.core.data.Status
import com.erkutaras.statelayout.StateLayout

data class FavoritePageStatusViewState(
        val status: Status = Status.LoadingWithContent
) {

    fun getStateInfo(context: Context): StateLayout.StateInfo =
            status.getStateInfo(
                    onEmpty = {
                        StateLayout.StateInfo(
                                infoImage = R.drawable.image_empty,
                                infoMessage = context.getString(R.string.favorite_page_empty_message),
                                infoButtonText = context.getString(R.string.go_to_search_button_text)
                        )
                    },
                    onError = { StateLayout.provideContentStateInfo() }
            )
}