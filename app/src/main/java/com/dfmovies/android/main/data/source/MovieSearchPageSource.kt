package com.dfmovies.android.main.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import com.dfmovies.android.core.util.extension.orFalse
import com.dfmovies.android.core.util.extension.orZero
import com.dfmovies.android.main.data.MovieRepository
import com.dfmovies.android.main.domain.mapper.MovieMapper
import com.dfmovies.android.main.domain.model.Movie
import com.dfmovies.android.main.domain.model.MovieFilterDefinition

class MovieSearchPageSource(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper,
    private val filterMap: Map<String, String>
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: INITIAL_PAGE
            val queryMap = filterMap.toMutableMap()
            queryMap[MovieFilterDefinition.PAGE] = nextPage.toString()

            val response = movieRepository.searchMovie(queryMap)

            Page(
                data = movieMapper.mapOnResponse(response),
                prevKey = if (nextPage == INITIAL_PAGE) null else nextPage - 1,
                nextKey = if (response.total_pages.orZero() == nextPage) {
                    null
                } else {
                    nextPage + 1
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_PAGE = 1
    }
}