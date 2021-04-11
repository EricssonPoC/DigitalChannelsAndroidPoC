package com.dfmovies.android.main.ui.adapter

import com.dfmovies.android.main.domain.model.Movie

data class SearchItemViewState(val movie: Movie) {

    fun getText(): String {
        return "${movie.title} - ${movie.year}"
    }
}
