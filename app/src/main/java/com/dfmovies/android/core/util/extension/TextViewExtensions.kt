package com.dfmovies.android.core.util.extension

import android.graphics.Rect
import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

const val TEXT_PAINT_EXTRA_PADDING_FACTOR = 10

fun TextView.getDisplayedText(): String {
    var visibleEnd = layout.getLineEnd(maxLines - 1)
    if (text.length < visibleEnd) {
        visibleEnd = text.length
    }
    return text.toString().substring(visibleEnd)
}

fun TextView.isEllipsized(): Boolean {
    layout?.run {
        val lastLine = lineCount - 1
        return getEllipsisCount(lastLine) != 0
    } ?: return false
}

fun TextView.addPaintFlag(flag: Int) {
    paintFlags = paintFlags or flag
}

fun TextView.removePaintFlag(flag: Int) {
    paintFlags = paintFlags and flag.inv()
}

fun TextView.getVisibleLineCount(): Int {
    return height / lineHeight
}

fun TextView.hideWhenTextIsNotFullyVisible(vararg otherViewsToHide: View) {
    doOnLayout {
        val rect = Rect().also {
            paint.getTextBounds(text?.toString() ?: "", 0, text?.length ?: 0, it)
        }.also {
            it.set(
                it.left + paddingLeft,
                it.top + paddingTop,
                it.right + paddingRight + TEXT_PAINT_EXTRA_PADDING_FACTOR,
                it.bottom + paddingBottom
            )
        }
        val visibilityRect = Rect().also { getGlobalVisibleRect(it) }

        visibility =
            (if (visibilityRect.width() >= rect.width()) View.VISIBLE else View.GONE).also {
                otherViewsToHide.forEach { view -> view.visibility = it }
            }
    }
}

fun TextView.getClickableSpanText(clickableSpan: ClickableSpan): String {
    val spanned = text as Spanned?
    val start = spanned?.getSpanStart(clickableSpan)
    val end = spanned?.getSpanEnd(clickableSpan)
    return spanned?.subSequence(start.orZero(), end.orZero()).toString()
}

fun TextView.showKeyboardWithFocus() {
    with(this) {
        requestFocus()
        showKeyboard()
    }
}

fun TextView.showKeyboardWithDelay() {
    with(this) {
        requestFocus()
        postDelayed({ showKeyboard() }, 100L)
    }
}