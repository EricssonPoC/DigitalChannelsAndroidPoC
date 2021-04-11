package com.dfmovies.android.core.util.extension

import android.annotation.SuppressLint
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout

@SuppressLint("ClickableViewAccessibility")
fun NestedScrollView.setChildViewOnScreenListener(view: View, action: () -> Unit) {
    val visibleScreen = Rect()

    this.setOnTouchListener { _, motionEvent ->
        Log.e("action", "${motionEvent.action}")
        if (motionEvent.action == MotionEvent.ACTION_MOVE || motionEvent.action == MotionEvent.ACTION_UP) {
            this.getDrawingRect(visibleScreen)

            if (view.getLocalVisibleRect(visibleScreen)) {
                action()
            }
        }

        false
    }
}

fun NestedScrollView.setChildViewOnScreenScrollListener(view: View, action: () -> Unit) {
    val scrollBounds = Rect()
    this.getHitRect(scrollBounds)
    this.setOnScrollChangeListener { _: NestedScrollView?, _: Int, _: Int, _: Int, _: Int ->
        if (view.getLocalVisibleRect(scrollBounds)) {
            if (!view.getLocalVisibleRect(scrollBounds) ||
                scrollBounds.height() < view.height
            ) {
                action()
            }
        }
    }
}

fun NestedScrollView.isChildViewOnScreen(view: View, action: () -> Unit) {
    val visibleScreen = Rect()
    this.getDrawingRect(visibleScreen)

    if (view.getLocalVisibleRect(visibleScreen)) {
        action()
    }
}

fun NestedScrollView.smoothScrollWithDelay(
    startPosition: Int,
    endPosition: Int,
    delayTime: Long,
    appBarLayout: AppBarLayout? = null
) {
    this.postDelayed(
        {
            appBarLayout?.setExpanded(false)
            smoothScrollTo(startPosition, endPosition)
        },
        delayTime
    )
}

fun NestedScrollView.smoothScroll(
    startPosition: Int,
    endPosition: Int,
    appBarLayout: AppBarLayout? = null
) {
    appBarLayout?.setExpanded(false)
    smoothScrollTo(startPosition, endPosition)
}