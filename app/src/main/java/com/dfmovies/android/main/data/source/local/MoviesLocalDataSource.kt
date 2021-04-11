package com.dfmovies.android.main.data.source.local

import com.dfmovies.android.main.data.source.MovieDataSource
import com.dfmovies.android.main.data.source.local.dao.MovieDao
import com.dfmovies.android.main.domain.model.Movie
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
        private val movieDao: MovieDao
) : MovieDataSource.Local {

    override suspend fun getMovies(): List<Movie> = movieDao.getMovies()

    override suspend fun getMovie(movieId: Int): Movie? = movieDao.getMovie(movieId)

    override suspend fun updateMovie(movie: Movie) = movieDao.update(movie)

    override suspend fun addMovie(movie: Movie) = movieDao.insert(movie)

    override suspend fun getWatchlistMovies(): List<Movie> = movieDao.getWatchlistMovies()

    override suspend fun getFavoriteMovies(): List<Movie> = movieDao.getFavoriteMovies()

}