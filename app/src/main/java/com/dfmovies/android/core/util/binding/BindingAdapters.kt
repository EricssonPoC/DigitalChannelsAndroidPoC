package com.dfmovies.android.core.util.binding

import android.content.res.ColorStateList
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("isInvisible")
fun View.isInVisible(isInvisible: Boolean) {
    visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("backgroundDrawable")
fun View.backgroundDrawable(@DrawableRes drawableRes: Int) {
    this.background = ContextCompat.getDrawable(context, drawableRes)
}

@BindingAdapter("backgroundColor")
fun View.backgroundColor(@ColorRes colorRes: Int) {
    this.setBackgroundColor(ContextCompat.getColor(context, colorRes))
}

@BindingAdapter("backgroundColorInt")
fun View.backgroundColorInt(@ColorInt colorRes: Int) {
    this.setBackgroundColor(colorRes)
}

@BindingAdapter(
    value = ["paddingEnd", "paddingTop", "paddingStart", "paddingBottom"],
    requireAll = false
)
fun View.setPadding(
    paddingEnd: Int = 0,
    paddingTop: Int = 0,
    paddingStart: Int = 0,
    paddingBottom: Int = 0
) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

@BindingAdapter(
    value = ["marginEnd", "marginTop", "marginStart", "marginBottom"],
    requireAll = false
)
fun View.setMargin(
    marginEnd: Int = 0,
    marginTop: Int = 0,
    marginStart: Int = 0,
    marginBottom: Int = 0
) {
    val layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(marginEnd, marginTop, marginStart, marginBottom)
    }

    this.layoutParams = layoutParams
}

@BindingAdapter("setMarginEnd")
fun View.setMarginEnd(marginEnd: Int = 0) {
    val layoutParams = this.layoutParams as? FrameLayout.LayoutParams
    layoutParams?.marginEnd = marginEnd
    this.layoutParams = layoutParams
}

@BindingAdapter("srcEditTextBackground")
fun setEditTextBackground(editText: AppCompatEditText, @DrawableRes drawable: Int) {
    if (drawable != -1) editText.setBackgroundResource(drawable)
}

@BindingAdapter("srcTexViewBackground")
fun setTextViewBackground(editText: AppCompatTextView, @DrawableRes drawable: Int) {
    if (drawable != -1) editText.setBackgroundResource(drawable)
}

@BindingAdapter("srcButtonBackground")
fun setMaterialButtonBackground(button: MaterialButton, @DrawableRes drawable: Int) {
    if (drawable != -1) button.background =
        ResourcesCompat.getDrawable(button.context.resources, drawable, null)
}

@BindingAdapter("setCardElevation")
fun MaterialCardView.setElevation(elevation: Float) {
    this.cardElevation = elevation
}

@BindingAdapter("setCardBackgroundColor")
fun MaterialCardView.setCardBackgroundColor(color: Int) {
    this.setCardBackgroundColor(ContextCompat.getColor(this.context, color))
}

@BindingAdapter("setBackgroundTint")
fun MaterialButton.setBackgroundTint(colorStateList: ColorStateList?) {
    this.backgroundTintList = colorStateList
}

@BindingAdapter("buttonIcon")
fun MaterialButton.setButtonIcon(@DrawableRes drawable: Int) {
    setIconResource(drawable)
}
