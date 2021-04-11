package com.dfmovies.android.core.util.extension

import java.nio.ByteBuffer

fun ByteBuffer.toByteArray(): ByteArray {
    rewind()
    val data = ByteArray(remaining())
    get(data)
    return data
}