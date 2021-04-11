package com.dfmovies.android.core.domain.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dfmovies.android.main.data.source.local.dao.MovieDao
import com.dfmovies.android.main.domain.model.Movie

@Database(
    entities = [
        Movie::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DfMoviesDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao

}