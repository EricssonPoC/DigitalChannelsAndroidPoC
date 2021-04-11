package com.dfmovies.android.main.ui.detail

import com.dfmovies.android.R
import com.dfmovies.android.main.domain.model.Movie

data class MovieDetailPageViewState(val movie: Movie) {

    fun getCoverImageUrl(): String {
        return movie.coverImageUrl
    }

    fun getOriginalTitle(): String {
        return movie.originalTitle
    }

    fun getVoteRate(): String {
        return movie.totalVote.toString()
    }

    fun getDate(): String {
        return movie.date
    }

    fun getDescription(): String {
        return movie.description
    }

    fun getFavoriteIcon(): Int {
        return if (movie.isInFavorite) {
            R.drawable.ic_tab_favourite_selected
        } else {
            R.drawable.ic_tab_favourite
        }
    }

    fun getWatchlistIcon(): Int {
        return if (movie.isInWatchList) {
            R.drawable.ic_tab_watchlist_selected
        } else {
            R.drawable.ic_tab_watchlist
        }
    }
}