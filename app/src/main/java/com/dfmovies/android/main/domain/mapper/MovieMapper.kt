package com.dfmovies.android.main.domain.mapper

import com.dfmovies.android.BuildConfig
import com.dfmovies.android.core.util.extension.getFormattedDateString
import com.dfmovies.android.core.util.extension.getYear
import com.dfmovies.android.core.util.extension.orZero
import com.dfmovies.android.main.data.source.remote.model.MovieItemResponse
import com.dfmovies.android.main.data.source.remote.model.MoviesResponse
import com.dfmovies.android.main.domain.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun mapOnResponse(response: MoviesResponse): List<Movie> {
        return response.results?.map {
            mapOnMovieItemResponse(it)
        } ?: emptyList()
    }

    private fun mapOnMovieItemResponse(response: MovieItemResponse?): Movie {
        return Movie(
                id = response?.id.orZero(),
                title = response?.title.orEmpty(),
                originalTitle = response?.original_title.orEmpty(),
                year = response?.release_date?.getYear().orEmpty(),
                date = response?.release_date?.getFormattedDateString().orEmpty(),
                totalVote = response?.vote_average.orZero(),
                coverImageUrl = BuildConfig.IMAGE_BASE_URL.plus(response?.backdrop_path.orEmpty()),
                thumbnailImageUrl = BuildConfig.IMAGE_BASE_URL.plus(response?.poster_path.orEmpty()),
                description = response?.overview.orEmpty(),
                isInFavorite = false,
                isInWatchList = false

        )
    }
}