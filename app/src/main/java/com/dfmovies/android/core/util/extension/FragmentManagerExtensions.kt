package com.dfmovies.android.core.util.extension

import androidx.fragment.app.FragmentManager

fun FragmentManager.fragmentIsVisibleOrAdded(tag: String): Boolean {
    val fragment = this.findFragmentByTag(tag)
    return fragment?.isAdded.orFalse() || fragment?.isVisible.orFalse()
}

fun FragmentManager.fragmentIsVisibleOrAdded(id: Int): Boolean {
    val fragment = this.findFragmentById(id)
    return fragment?.isAdded.orFalse() || fragment?.isVisible.orFalse()
}