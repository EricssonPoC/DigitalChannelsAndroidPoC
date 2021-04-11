package com.dfmovies.android.main.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.dfmovies.android.R
import com.dfmovies.android.core.util.extension.inflate
import com.dfmovies.android.core.util.recyclerview.DataClassDiffCallback
import com.dfmovies.android.main.domain.model.Movie
import javax.inject.Inject

class SearchMoviesPagingAdapter @Inject constructor() :
    PagingDataAdapter<Movie, SearchItemViewHolder>(DataClassDiffCallback { it.originalTitle }) {

    var itemClickListener: ((Movie) -> Unit)? = null

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(
            parent.inflate(R.layout.item_search, false),
            itemClickListener
        )
    }
}