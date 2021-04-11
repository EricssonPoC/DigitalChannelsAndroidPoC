package com.dfmovies.android.core.util.extension

import android.os.Build

fun isLollipopOrHigher() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isMOrHigher() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun isOreoOrHigher() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isOreo() = Build.VERSION.SDK_INT == Build.VERSION_CODES.O

fun isQOrHigher() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
