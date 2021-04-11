package com.dfmovies.android.core.util.extension

import android.animation.ArgbEvaluator
import android.content.ContentResolver
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getUriFromResources(folder: String, fileName: String): Uri? {
    val androidScheme = ContentResolver.SCHEME_ANDROID_RESOURCE
    return Uri.parse("$androidScheme://$packageName/$folder/$fileName")
}

fun Context.layoutInflater(): LayoutInflater {
    return LayoutInflater.from(this)
}

@ColorInt
fun Context.color(@ColorRes colorResId: Int): Int =
    ContextCompat.getColor(this, colorResId)

fun Context.pixel(@DimenRes dimenResId: Int): Int =
    resources.getDimensionPixelSize(dimenResId)

fun Context.drawable(@DrawableRes drawableResId: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableResId)

fun getDeviceWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun getDeviceHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}

fun Context.getPixels(valueInDp: Int): Int {
    val r = resources
    val px = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        valueInDp.toFloat(),
        r.displayMetrics
    )
    return px.toInt()
}

fun Context.getPixels(valueInDp: Float): Int {
    val r = resources
    val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, r.displayMetrics)
    return px.toInt()
}

fun Context.getPixelsSp(valueInSp: Int): Int {
    val r = resources
    val px =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, valueInSp.toFloat(), r.displayMetrics)
    return px.toInt()
}

fun Context.getPixelsSp(valueInSp: Float): Int {
    val r = resources
    val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, valueInSp, r.displayMetrics)
    return px.toInt()
}

fun Context.showShortToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.evaluateArgbInt(
    fraction: Float,
    @ColorRes startValue: Int,
    @ColorRes endValue: Int
): Int =
    ArgbEvaluator().evaluate(
        fraction,
        ContextCompat.getColor(
            this,
            startValue
        ),
        ContextCompat.getColor(
            this,
            endValue
        )
    ) as Int

fun Context.evaluateArgbColorStateList(
    fraction: Float,
    @ColorRes startValue: Int,
    @ColorRes endValue: Int
) = ColorStateList.valueOf(evaluateArgbInt(fraction, startValue, endValue))

// Extension method to vibrate a phone programmatically
fun Context.vibrate(milliseconds: Long = 500) {
    val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    // Check whether device/hardware has a vibrator
    val canVibrate: Boolean = vibrator.hasVibrator()

    if (canVibrate) {
        if (isOreoOrHigher()) {
            // void vibrate (VibrationEffect vibe)
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    milliseconds,
                    // The default vibration strength of the device.
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            // This method was deprecated in API level 26
            vibrator.vibrate(milliseconds)
        }
    }
}