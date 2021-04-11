package com.dfmovies.android.main.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dfmovies.android.main.data.MovieRepository
import com.dfmovies.android.main.data.source.MovieDiscoverPageSource
import com.dfmovies.android.main.data.source.MovieSearchPageSource
import com.dfmovies.android.main.domain.mapper.MovieMapper
import com.dfmovies.android.main.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
        private val movieRepository: MovieRepository,
        private val movieMapper: MovieMapper
) {

    fun searchMovie(
            filterMap: Map<String, String>
    ): Flow<PagingData<Movie>> {
        return Pager(
                config = PagingConfig(pageSize = SIZE_PER_PAGE, enablePlaceholders = false),
                pagingSourceFactory = {
                    MovieSearchPageSource(
                            movieRepository = movieRepository,
                            movieMapper = movieMapper,
                            filterMap = filterMap
                    )
                }
        ).flow
    }

    fun discoverMovie(
            filterMap: Map<String, String>
    ): Flow<PagingData<Movie>> {
        return Pager(
                config = PagingConfig(pageSize = SIZE_PER_PAGE, enablePlaceholders = false),
                pagingSourceFactory = {
                    MovieDiscoverPageSource(
                            movieRepository = movieRepository,
                            movieMapper = movieMapper,
                            filterMap = filterMap
                    )
                }
        ).flow
    }

    suspend fun getFavoriteMoviesFromDb(): List<Movie> {
        return movieRepository.getFavoriteMoviesFromDb()
    }

    suspend fun getWatchlistMoviesFromDb(): List<Movie> {
        return movieRepository.getWatchlistMoviesFromDb()
    }

    suspend fun getMovie(movieId: Int): Movie? {
        return movieRepository.getMovie(movieId)
    }

    suspend fun updateMovie(movie: Movie) {
        movieRepository.updateMovie(movie)
    }

    suspend fun addMovie(movie: Movie) {
        movieRepository.addMovie(movie)
    }

    companion object {
        private const val SIZE_PER_PAGE = 20
    }
}