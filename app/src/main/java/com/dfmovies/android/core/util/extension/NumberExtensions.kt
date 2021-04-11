package com.dfmovies.android.core.util.extension

import java.text.DecimalFormat

fun Int?.greaterThan(value: Int, includeSelf: Boolean = false): Boolean {
    return if (this == null) false
    else {
        if (includeSelf) {
            this >= value
        } else {
            this > value
        }
    }
}

fun Int?.smallerThan(value: Int): Boolean =
    if (this == null) false else this < value

fun Int?.orZero(): Int = this ?: 0

fun Double?.orZero(): Double = this ?: 0.0

fun Double.isZero(): Boolean = this == 0.0

fun Double.isNotZero(): Boolean = this != 0.0

fun Long?.orZero(): Long = this ?: 0L

fun Int?.orOne(): Int = this ?: 1

fun Double?.orOne(): Double = this ?: 1.0

fun Long?.orOne(): Long = this ?: 1L

fun Int.greaterThan(number: Int): Boolean = this > number

fun Long?.isNull(): Boolean = this == null

/**
 * Example : 01:59
 */
@Suppress("MagicNumber")
fun Long.getDefaultTimerText(): String {
    return "${(this / 1000 / 60).toString().padStart(2, '0')}:" +
        (this / 1000 % 60).toString().padStart(2, '0')
}

/**
 * Example : 59
 */
@Suppress("MagicNumber")
fun Long.getMinutesTimerText(): String {
    return (this / 1000 / 60).toString()
}

@Suppress("MagicNumber")
fun Long.isMinutesEqualZero(): Boolean {
    return (this / 1000 / 60) == 0L
}

fun Double.getFormattedPrice(): String {
    val fmt = DecimalFormat.getInstance()
    fmt.minimumFractionDigits = 2
    fmt.maximumFractionDigits = 2
    return fmt.format(this)
}
