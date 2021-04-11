package com.dfmovies.android.main.domain.model

import androidx.annotation.StringDef
import com.dfmovies.android.main.domain.model.MovieFilterDefinition.Companion.PAGE
import com.dfmovies.android.main.domain.model.MovieFilterDefinition.Companion.QUERY

@StringDef(PAGE, QUERY)
annotation class MovieFilterDefinition {
    companion object {
        const val PAGE = "page"
        const val QUERY = "query"
    }
}