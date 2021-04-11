@file:JvmName("LiveDataExtensions")

package com.dfmovies.android.core.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(
        owner,
        Observer {
            it?.let(observer)
        }
    )
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(
        owner,
        Observer {
            observer(it)
        }
    )
}
