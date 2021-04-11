package com.dfmovies.android.core.util.extension

fun Boolean?.orTrue() = this ?: true

fun Boolean?.orFalse() = this ?: false

fun Boolean.reverse() = this.not()