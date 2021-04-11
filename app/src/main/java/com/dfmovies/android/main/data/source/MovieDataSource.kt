package com.dfmovies.android.main.data.source

import com.dfmovies.android.main.data.source.remote.model.MoviesResponse
import com.dfmovies.android.main.domain.model.Movie

interface MovieDataSource {

    interface Local {
        suspend fun getMovies(): List<Movie>

        suspend fun getWatchlistMovies(): List<Movie>

        suspend fun getFavoriteMovies(): List<Movie>

        suspend fun getMovie(movieId: Int): Movie?

        suspend fun updateMovie(movie: Movie)

        suspend fun addMovie(movie: Movie)
    }

    interface Remote {

        suspend fun searchMovie(queryMap: Map<String, String>): MoviesResponse

        suspend fun discoverMovies(queryMap: Map<String, String>): MoviesResponse
    }
}