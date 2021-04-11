package com.dfmovies.android.core.util.extension

inline fun <E : Any, T : Collection<E>> T?.whenNullOrEmpty(func: () -> T?): T? {
    if (this == null || this.isEmpty()) {
        return func()
    }
    return this
}
