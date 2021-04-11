package com.dfmovies.android.main.data.source.remote

import com.dfmovies.android.BuildConfig
import com.dfmovies.android.main.data.source.remote.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieService {

    @GET("search/movie?api_key=${BuildConfig.API_KEY}")
    suspend fun searchMovie(@QueryMap queryMap: Map<String,String>): MoviesResponse

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}")
    suspend fun discoverMovies(@QueryMap queryMap: Map<String,String>): MoviesResponse
}