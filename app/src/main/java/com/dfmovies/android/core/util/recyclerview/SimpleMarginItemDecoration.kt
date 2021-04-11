package com.dfmovies.android.core.util.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class SimpleMarginItemDecoration constructor(
    @Px private val left: Int = 0,
    @Px private val top: Int = 0,
    @Px private val right: Int = 0,
    @Px private val bottom: Int = 0
) : RecyclerView.ItemDecoration() {

    constructor(@Px margin: Int) : this(margin, margin, margin, margin)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        val topMargin = top.takeIf { position == 0 } ?: top / 2
        val bottomMargin = bottom.takeIf { position == itemCount - 1 } ?: bottom / 2
        outRect.set(left, topMargin, right, bottomMargin)
    }
}