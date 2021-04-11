package com.dfmovies.android.core.util.extension

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

fun <T> List<T>.centerItemPosition() = this.size / 2

fun <T> MutableList<T>.addAllNotContains(list: List<T>) {
    list.map { item ->
        if (this.contains(item).not()) {
            this.add(item)
        }
    }
}