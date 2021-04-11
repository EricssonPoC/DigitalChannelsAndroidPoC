package com.dfmovies.android.main.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieItemResponse>?,
    @SerializedName("total_pages") val total_pages: Int?,
    @SerializedName("total_results") val total_results: Int?
)