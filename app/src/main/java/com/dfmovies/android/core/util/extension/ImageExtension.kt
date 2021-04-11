package com.dfmovies.android.core.util.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

fun ImageView.loadImage(url: String) {
    Glide.with(context).asBitmap().load(url.removeAllSpaces()).into(this)
}

fun ImageView.loadGif(
    @RawRes @DrawableRes
    resourceId: Int
) {
    Glide.with(context).asGif().load(resourceId).into(this)
}

fun ImageView.loadImage(glide: RequestManager, url: String) {
    glide.load(url.removeAllSpaces()).into(this)
}
