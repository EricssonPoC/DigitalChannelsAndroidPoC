package com.dfmovies.android.core.util.recyclerview

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class SimpleDividerItemDecoration(
    context: Context,
    orientation: Int,
    private val skipLastItem: Boolean
) :
    DividerItemDecoration(context, orientation) {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (skipLastItem && isLastItem(view, parent, state)) {
            outRect.setEmpty()
        } else {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }

    private fun isLastItem(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean =
        parent.getChildAdapterPosition(view) == state.itemCount.minus(1)
}