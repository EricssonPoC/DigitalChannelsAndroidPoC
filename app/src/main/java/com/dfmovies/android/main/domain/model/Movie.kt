package com.dfmovies.android.main.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class Movie(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "originalTitle")
        val originalTitle: String,
        @ColumnInfo(name = "year")
        val year: String,
        @ColumnInfo(name = "totalVote")
        val totalVote: Double,
        @ColumnInfo(name = "date")
        val date: String,
        @ColumnInfo(name = "coverImageUrl")
        val coverImageUrl: String,
        @ColumnInfo(name = "thumbnailImageUrl")
        val thumbnailImageUrl: String,
        @ColumnInfo(name = "description")
        val description: String,
        @ColumnInfo(name = "in_watchlist")
        val isInWatchList: Boolean,
        @ColumnInfo(name = "in_favorite")
        val isInFavorite: Boolean
) : Parcelable
