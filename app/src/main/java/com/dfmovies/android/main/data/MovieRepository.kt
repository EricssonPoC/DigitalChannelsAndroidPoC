package com.dfmovies.android.main.data

import com.dfmovies.android.main.data.source.local.MoviesLocalDataSource
import com.dfmovies.android.main.data.source.remote.MovieRemoteDataSource
import com.dfmovies.android.main.data.source.remote.model.MoviesResponse
import com.dfmovies.android.main.domain.model.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
        private val remoteDataSource: MovieRemoteDataSource,
        private val localDataSource: MoviesLocalDataSource
) {

    suspend fun searchMovie(queryMap: Map<String, String>): MoviesResponse {
        return remoteDataSource.searchMovie(queryMap)
    }

    suspend fun discoverMovies(queryMap: Map<String, String>): MoviesResponse {
        return remoteDataSource.discoverMovies(queryMap)
    }
    suspend fun getFavoriteMoviesFromDb(): List<Movie> {
        return localDataSource.getFavoriteMovies()
    }

    suspend fun getWatchlistMoviesFromDb(): List<Movie> {
        return localDataSource.getWatchlistMovies()
    }

    suspend fun getMovie(movieId: Int): Movie? = localDataSource.getMovie(movieId)

    suspend fun updateMovie(movie: Movie) = localDataSource.updateMovie(movie)

    suspend fun addMovie(movie: Movie) = localDataSource.addMovie(movie)

}