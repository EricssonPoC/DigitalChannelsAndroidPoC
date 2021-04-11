package com.dfmovies.android.main.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dfmovies.android.databinding.ItemSearchBinding
import com.dfmovies.android.main.domain.model.Movie

class SearchItemViewHolder(
    private val binding: ItemSearchBinding,
    itemClickListener: ((Movie) -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            binding.viewState?.movie?.let { friend ->
                itemClickListener?.invoke(friend)
            }
        }
    }

    fun bind(movie: Movie) {
        with(binding) {
            viewState = SearchItemViewState(movie)
            executePendingBindings()
        }
    }
}