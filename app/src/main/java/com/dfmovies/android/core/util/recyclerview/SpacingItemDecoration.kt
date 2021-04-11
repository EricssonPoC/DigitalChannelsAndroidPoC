package com.dfmovies.android.core.util.recyclerview

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dfmovies.android.R
import com.dfmovies.android.core.util.extension.isLollipopOrHigher

/**
 * https://gist.github.com/alexfu/f7b8278009f3119f523a
 */
class SpacingItemDecoration @JvmOverloads constructor(
    context: Context,
    private var displayMode: Int = -1,
    marginValue: Int = R.dimen.margin_16dp,
    private val skipFirstItem: Boolean = false
) : RecyclerView.ItemDecoration() {
    private val spacing: Int = context.resources.getDimension(marginValue).toInt()
    private var elevationSpacing = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager
        setSpacingForDirection(view, outRect, layoutManager, position, itemCount)
    }

    @Suppress("ComplexMethod")
    private fun setSpacingForDirection(
        view: View,
        outRect: Rect,
        layoutManager: RecyclerView.LayoutManager?,
        position: Int,
        itemCount: Int
    ) {
        if (displayMode == -1) {
            displayMode = resolveDisplayMode(layoutManager)
        }

        if (isLollipopOrHigher()) {
            elevationSpacing = view.elevation.toInt()
        }

        when (displayMode) {
            HORIZONTAL -> {
                outRect.left = if (position == 0 && skipFirstItem) 0 else spacing
                outRect.right = if (position == itemCount - 1) 0 else spacing
                outRect.top = elevationSpacing
                outRect.bottom = if (skipFirstItem && position == 0) 0 else elevationSpacing
            }
            VERTICAL -> {
                outRect.top = if (position == 0 && skipFirstItem) 0 else spacing
                outRect.bottom = if (position == itemCount - 1) spacing else 0
            }
            GRID -> if (layoutManager is GridLayoutManager) {
                val spanCount = layoutManager.spanCount
                val rows = itemCount / spanCount

                val currentColumn = layoutManager.spanSizeLookup.getSpanIndex(position, spanCount)
                val currentRow = layoutManager.spanSizeLookup.getSpanGroupIndex(position, spanCount)

                outRect.top = if (currentRow == 0) 0 else spacing
                outRect.bottom = if (currentRow == rows - 1) elevationSpacing else 0
                if (skipFirstItem && position == 0) {
                    outRect.left = 0
                    outRect.right = 0
                } else {
                    outRect.left = spacing - currentColumn * spacing / spanCount
                    outRect.right = (currentColumn + 1) * spacing / spanCount
                }
            }
        }
    }

    private fun resolveDisplayMode(layoutManager: RecyclerView.LayoutManager?): Int {
        if (layoutManager is GridLayoutManager) return GRID
        return if (layoutManager?.canScrollHorizontally() == true) HORIZONTAL else VERTICAL
    }

    @Suppress("MayBeConst")
    companion object {

        @JvmField
        val HORIZONTAL = 0

        @JvmField
        val VERTICAL = 1

        @JvmField
        val GRID = 2
    }
}