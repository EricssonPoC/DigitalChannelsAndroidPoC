package com.dfmovies.android.main.data.source.local.dao

import androidx.room.*
import com.dfmovies.android.main.domain.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE in_favorite=1")
    suspend fun getFavoriteMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE in_watchlist=1")
    suspend fun getWatchlistMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE id=:movieId")
    suspend fun getMovie(movieId: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Query("DELETE FROM movies WHERE id=:movieId")
    suspend fun delete(movieId: Int)

}