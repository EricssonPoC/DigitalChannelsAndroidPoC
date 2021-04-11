package com.dfmovies.android.main.ui.detail

import android.os.Parcelable
import com.dfmovies.android.main.domain.model.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailFragmentArgument(
    val movie: Movie
) : Parcelable