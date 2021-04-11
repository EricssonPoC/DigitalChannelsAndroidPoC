package com.dfmovies.android.core.util.extension

import android.app.Activity
import android.content.pm.ResolveInfo
import android.view.View
import android.view.Window
import android.view.WindowManager

fun Activity.getStatusBarHeightInPx(): Int {
    var result = 0
    val resourceId: Int =
        this.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = this.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Activity.adjustPan() {
    window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
}

fun Activity.adjustResize() {
    window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}

fun Activity.adjustNothing() {
    window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
}

fun Window.getSoftInputMode(): Int {
    return attributes.softInputMode
}

fun Activity.drawBehindStatusBar() {
    if (isLollipopOrHigher()) {
        val flags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        addSystemUIVisibilityFlags(flags)
    }
}

fun Activity.drawBelowStatusBar() {
    if (isLollipopOrHigher()) {
        val flags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        clearSystemUIVisibilityFlags(flags)
    }
}

fun Activity.isDrawingBelowStatusBar(): Boolean {
    val flags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    val windowFlags = window.decorView.systemUiVisibility
    val windowFlagsWithoutDrawBehindStatusBar = windowFlags.and(flags.inv())
    return windowFlags == windowFlagsWithoutDrawBehindStatusBar
}

private fun Activity.addSystemUIVisibilityFlags(flags: Int) {
    window.decorView.systemUiVisibility = window.decorView.systemUiVisibility.or(flags)
}

private fun Activity.clearSystemUIVisibilityFlags(flags: Int) {
    window.decorView.systemUiVisibility = window.decorView.systemUiVisibility.and(flags.inv())
}

fun Activity.getApplicationLabel(application: ResolveInfo): String {
    return application.loadLabel(packageManager).toString()
}

