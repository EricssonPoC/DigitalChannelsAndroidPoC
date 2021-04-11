package com.dfmovies.android.main.ui.adapter

import com.dfmovies.android.main.domain.model.Movie

data class MovieItemViewState(val movie: Movie) {

    fun getText(): String {
        return movie.title
    }

    fun getImageUrl(): String {
        return movie.thumbnailImageUrl
    }
}
