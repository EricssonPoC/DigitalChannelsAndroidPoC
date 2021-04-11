package com.dfmovies.android.main.data.source.remote

import com.dfmovies.android.main.data.source.MovieDataSource
import com.dfmovies.android.main.data.source.remote.model.MoviesResponse
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) : MovieDataSource.Remote {

    override suspend fun searchMovie(queryMap: Map<String, String>): MoviesResponse {
        return movieService.searchMovie(queryMap)
    }

    override suspend fun discoverMovies(queryMap: Map<String, String>): MoviesResponse {
        return movieService.discoverMovies(queryMap)
    }
}