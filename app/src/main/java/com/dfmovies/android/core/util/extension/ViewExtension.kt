package com.dfmovies.android.core.util.extension

import android.content.Context
import android.graphics.Rect
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.CompoundButton
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.switchmaterial.SwitchMaterial
import com.dfmovies.android.core.util.listener.ThrottledClickListener

fun <T : ViewDataBinding> ViewGroup?.inflate(
    @LayoutRes layoutId: Int,
    attachToParent: Boolean = true
): T {
    if (this?.isInEditMode == true) {
        View.inflate(context, layoutId, this)
    }
    return DataBindingUtil.inflate(
        LayoutInflater.from(this!!.context),
        layoutId,
        this,
        attachToParent
    )
}

fun <T : ViewDataBinding> View?.inflate(
    @LayoutRes layoutId: Int,
    attachToParent: Boolean = true
): T {
    return DataBindingUtil.inflate(
        LayoutInflater.from(this!!.context),
        layoutId,
        parent as? ViewGroup,
        attachToParent
    )
}

fun View.doOnNextLayout(action: (view: View) -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(
            view: View,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            view.removeOnLayoutChangeListener(this)
            action(view)
        }
    })
}

fun View.doOnLayout(action: (view: View) -> Unit) {
    if (ViewCompat.isLaidOut(this) && !isLayoutRequested) {
        action(this)
    } else {
        doOnNextLayout {
            action(it)
        }
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

@BindingAdapter("touchPadding")
fun View.setTouchPadding(extraSpaceInPx: Float) {
    val parentView = parent as? View
    parentView?.post {
        val hitRect = Rect().also { getHitRect(it) }
        hitRect.inset(-extraSpaceInPx.toInt(), -extraSpaceInPx.toInt())
        parentView.touchDelegate = TouchDelegate(hitRect, this)
    }
}

@BindingAdapter("scrollFlags")
fun ViewGroup.setLayoutScrollFlags(scrollFlags: Int) {
    val layoutParams = layoutParams as? AppBarLayout.LayoutParams

    layoutParams?.let {
        it.scrollFlags = scrollFlags
        this.layoutParams = it
    }
}

fun View.setMargin(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) =
    (layoutParams as? ViewGroup.MarginLayoutParams)?.run {
        bottomMargin = bottom
        topMargin = top
        leftMargin = left
        rightMargin = right
    }

fun View.getBottomMargin() = (layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin ?: 0

fun View.clearMargins() = (layoutParams as? ViewGroup.MarginLayoutParams)?.run {
    bottomMargin = 0
    topMargin = 0
    leftMargin = 0
    rightMargin = 0
}

inline fun View.afterMeasured(crossinline f: View.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.hideWithAnimation(parent: ViewGroup) {
    val autoTransition = AutoTransition()
    TransitionManager.beginDelayedTransition(parent, autoTransition)
    this@hideWithAnimation.visibility = View.GONE
}

fun View.visibleWithAnimation(parent: ViewGroup) {
    this.visibility = View.VISIBLE
    val autoTransition = AutoTransition()
    TransitionManager.beginDelayedTransition(parent, autoTransition)
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.isOnScreen(rootView: View): Boolean {
    val visibleScreen = Rect()
    rootView.getDrawingRect(visibleScreen)
    return this.getLocalVisibleRect(visibleScreen)
}

fun View.isCompletelyOnScreen(rootView: View): Boolean {
    val visibleScreen = Rect()
    rootView.getDrawingRect(visibleScreen)
    return (visibleScreen.height() < this.height && this.getLocalVisibleRect(visibleScreen))
}

fun SwitchMaterial.onCheckChanged(
    checkChangeListener: CompoundButton.OnCheckedChangeListener,
    action: () -> Unit
) {
    setOnCheckedChangeListener(null)
    action()
    setOnCheckedChangeListener(checkChangeListener)
}

fun View.setThrottledClickListener(clickListener: (View) -> Unit) {
    this.setOnClickListener(object : ThrottledClickListener() {
        override fun onClicked(view: View) {
            clickListener(view)
        }
    })
}