package com.dfmovies.android.core.util.binding

import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dfmovies.android.core.util.recyclerview.BaseListAdapter

@BindingAdapter("recyclerViewItems")
internal fun <T> RecyclerView.setItems(items: List<T>?) {
    (adapter as BaseListAdapter<T, *>).submitList(items.orEmpty())
}

@BindingAdapter("nestedScrollingEnable")
fun RecyclerView.setNestedScrollingEnable(nestedScrollingEnable: Boolean) {
    ViewCompat.setNestedScrollingEnabled(this, nestedScrollingEnable)
}