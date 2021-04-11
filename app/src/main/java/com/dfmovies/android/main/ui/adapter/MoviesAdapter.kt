package com.dfmovies.android.main.ui.adapter

import android.view.ViewGroup
import com.dfmovies.android.R
import com.dfmovies.android.core.util.extension.inflate
import com.dfmovies.android.core.util.recyclerview.BaseListAdapter
import com.dfmovies.android.core.util.recyclerview.DataClassDiffCallback
import com.dfmovies.android.main.domain.model.Movie
import javax.inject.Inject

class MoviesAdapter @Inject constructor() :
        BaseListAdapter<Movie, MovieViewHolder>(DataClassDiffCallback { it.id }) {

    var itemClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
            MovieViewHolder(
                    parent.inflate(R.layout.item_movie, false),
                    itemClickListener
            )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
            holder.bind(getItem(position))
}