package com.dfmovies.android.core.util.binding

import android.graphics.Paint
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import com.dfmovies.android.core.util.extension.addPaintFlag
import com.dfmovies.android.core.util.extension.removePaintFlag

@BindingAdapter("clickableSpanText")
fun TextView.setClickableSpanText(charSequence: CharSequence?) {
    charSequence?.let {
        movementMethod = LinkMovementMethod.getInstance()
        text = charSequence
    }
}

@BindingAdapter("textStyle")
fun textStyle(textView: TextView, @StyleRes styleRes: Int) {
    TextViewCompat.setTextAppearance(textView, styleRes)
}

@BindingAdapter("strikeThruEnabled")
fun TextView.setStrikeThruEnabled(strikeThruEnabled: Boolean) {
    if (strikeThruEnabled) {
        addPaintFlag(Paint.STRIKE_THRU_TEXT_FLAG)
    } else {
        removePaintFlag(Paint.STRIKE_THRU_TEXT_FLAG)
    }
}

@BindingAdapter("underlineEnabled")
fun TextView.setUnderlineEnabled(underlineEnabled: Boolean) {
    if (underlineEnabled) {
        addPaintFlag(Paint.UNDERLINE_TEXT_FLAG)
    } else {
        removePaintFlag(Paint.UNDERLINE_TEXT_FLAG)
    }
}

@BindingAdapter("textColorResource")
fun TextView.setTextColorResource(@ColorRes colorRes: Int) {
    setTextColor(ContextCompat.getColor(context, colorRes))
}

@Suppress("MagicNumber")
@BindingAdapter(
    value = ["accountPriceText", "priceTextSize"],
    requireAll = false
)
fun TextView.setAccountPriceText(
    charSequence: CharSequence?,
    priceTextSize: Int = 0
) {
    charSequence?.let {
        val lastIndex = it.lastIndex + 1
        val startIndex = lastIndex - 3
        val spannableStringBuilder = SpannableStringBuilder(it)
        val textSize = if (priceTextSize == 0) 21 else priceTextSize
        spannableStringBuilder.setSpan(
            AbsoluteSizeSpan(textSize, true),
            startIndex,
            lastIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        text = spannableStringBuilder
    }
}

@BindingAdapter("drawableEndCompat")
fun TextView.drawableEndCompat(drawable: Int) {
    if (drawable != -1)
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawable, 0)
}