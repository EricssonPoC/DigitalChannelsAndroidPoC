package com.dfmovies.android.core.util.recyclerview

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class SmoothLinearLayoutManager(
    context: Context?,
    orientation: Int = RecyclerView.VERTICAL,
    reverseLayout: Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout) {

    private var speedPerPixel: Float = DEFAULT_SPEED_PER_PIXEL

    private var maxSmoothScrollItemCount: Int = DEFAULT_MAX_SMOOTH_SCROLL_ITEM_COUNT

    private var snapPreference: Int = DEFAULT_SNAP_PREFERENCE

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView?,
        state: RecyclerView.State?,
        position: Int
    ) {
        super.smoothScrollToPosition(recyclerView, state, position)

        val linearSmoothScroller: LinearSmoothScroller =
            object : LinearSmoothScroller(recyclerView?.context) {
                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                    return speedPerPixel
                }

                override fun calculateDyToMakeVisible(view: View?, snapPreference: Int): Int {
                    return super.calculateDyToMakeVisible(
                        view,
                        this@SmoothLinearLayoutManager.snapPreference
                    )
                }
            }

        if (getCurrentPositionDifferenceBetweenTargetPosition(position) > maxSmoothScrollItemCount) {
            scrollToPosition(position)
        } else {
            linearSmoothScroller.targetPosition = position
            startSmoothScroll(linearSmoothScroller)
        }
    }

    fun setSpeedPerPixel(speedPerPixel: Float) {
        this.speedPerPixel = speedPerPixel
    }

    fun setMaxSmoothScrollItemCount(maxSmoothScrollItemCount: Int) {
        this.maxSmoothScrollItemCount = maxSmoothScrollItemCount
    }

    fun setSnapPreference(snapPreference: Int) {
        this.snapPreference = snapPreference
    }

    private fun getCurrentPositionDifferenceBetweenTargetPosition(targetPosition: Int): Int {
        return targetPosition - findLastVisibleItemPosition()
    }

    companion object {
        private const val DEFAULT_SPEED_PER_PIXEL: Float = 0.5f
        private const val DEFAULT_MAX_SMOOTH_SCROLL_ITEM_COUNT: Int = 10
        private const val DEFAULT_SNAP_PREFERENCE = LinearSmoothScroller.SNAP_TO_END
    }
}