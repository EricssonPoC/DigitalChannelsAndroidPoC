package com.dfmovies.android.main.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

private const val INDEX_DISCOVER = 0
private const val INDEX_WATCHLIST = 1
private const val INDEX_FAVORITE = 2

@Parcelize
enum class MainTabs(val index: Int) : Parcelable {
    DISCOVER(INDEX_DISCOVER),
    WATCHLIST(INDEX_WATCHLIST),
    FAVORITE(INDEX_FAVORITE)
}