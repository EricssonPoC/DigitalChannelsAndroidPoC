package com.dfmovies.android.core.util.recyclerview

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dfmovies.android.R
import com.dfmovies.android.core.util.extension.isLollipopOrHigher

class ProductListingItemDecoration @JvmOverloads constructor(
    context: Context,
    private val skipFirstItem: Boolean = false,
    @DimenRes private val dimenRes: Int = R.dimen.margin_16dp
) : RecyclerView.ItemDecoration() {

    private var spacing: Int = context.resources.getDimension(dimenRes).toInt()
    private var elevationSpacing = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager as GridLayoutManager

        setSpacingToView(view, outRect, layoutManager, position, itemCount)
    }

    private fun setSpacingToView(
        view: View,
        outRect: Rect,
        layoutManager: GridLayoutManager,
        position: Int,
        itemCount: Int
    ) {
        if (isLollipopOrHigher()) {
            elevationSpacing = view.elevation.toInt()
        }

        val spanCount = layoutManager.spanCount
        val spanSizeLookup = layoutManager.spanSizeLookup.getSpanSize(position)
        val rows = itemCount / spanCount

        val currentColumn = layoutManager.spanSizeLookup.getSpanIndex(position, spanCount)
        val currentRow = layoutManager.spanSizeLookup.getSpanGroupIndex(position, spanCount)

        outRect.top = if (currentRow == 0) 0 else spacing
        outRect.bottom = if (currentRow == rows - 1) elevationSpacing else 0
        if ((skipFirstItem && position == 0) || (spanSizeLookup == 2)) {
            outRect.left = 0
            outRect.right = 0
        } else {
            outRect.left = spacing - currentColumn * spacing / spanCount
            outRect.right = (currentColumn + 1) * spacing / spanCount
        }
    }
}
