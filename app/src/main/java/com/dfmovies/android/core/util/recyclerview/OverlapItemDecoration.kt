package com.dfmovies.android.core.util.recyclerview

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.dfmovies.android.R
import javax.inject.Inject

class OverlapItemDecoration @Inject constructor(
    private val context: Context,
    @DimenRes private val overlapSize: Int = R.dimen.margin_4dp
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.left = context.resources.getDimensionPixelOffset(overlapSize).times(-1)
        }
    }
}