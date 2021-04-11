package com.dfmovies.android.core.util.binding

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dfmovies.android.R
import com.dfmovies.android.core.util.extension.getDeviceWidth
import com.dfmovies.android.core.util.extension.removeAllSpaces
import com.dfmovies.android.core.util.image.ImageViewType
import com.dfmovies.android.core.util.image.RequestOptionsFactory

@BindingAdapter("url")
fun loadUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView)
        .load(url?.removeAllSpaces())
        .into(imageView)
}

@Suppress("LongParameterList")
@BindingAdapter(
    value = ["imageUrl", "imageType", "imageRatio", "skipMemoryCache", "placeHolder"],
    requireAll = false
)
fun setUrl(
    imageView: ImageView,
    url: String?,
    imageViewType: ImageViewType?,
    imageRatio: Double?,
    skipMemoryCache: Boolean?,
    placeHolder: Drawable?
) {
    val requestOption = RequestOptionsFactory
        .create(imageViewType)
        .skipMemoryCache(skipMemoryCache == true)
    imageRatio?.let {
        if (it != 0.0) {
            val imageWidth =
                (getDeviceWidth()) - imageView.context.resources.getDimension(
                    R.dimen.margin_16dp
                )
            requestOption.override(imageWidth.toInt(), (imageWidth * it).toInt())
        }
    }
    Glide.with(imageView.context)
        .load(url?.removeAllSpaces())
        .placeholder(placeHolder)
        .error(R.drawable.image_no_poster)
        .apply(requestOption)
        .into(imageView)
}

@BindingAdapter("srcVector")
fun setSrcVector(imageView: ImageView, @DrawableRes drawable: Int) {
    if (drawable != -1) imageView.setImageResource(drawable)
}

@BindingAdapter("srcVectorTint")
fun setSrcVectorTint(imageView: ImageView, @ColorRes tintColor: Int) {
    if (tintColor != -1)
        imageView.imageTintList =
            ColorStateList.valueOf(ContextCompat.getColor(imageView.context, tintColor))
}

@BindingAdapter("imageDrawable")
fun ImageView.setDrawable(drawable: Drawable?) {
    setImageDrawable(drawable)
}

@BindingAdapter("imageBitmap")
fun ImageView.setBitmap(bitmap: Bitmap?) {
    Glide.with(this).asBitmap().load(bitmap).into(this)
}

@BindingAdapter("colorTint")
fun setColorTint(imageView: AppCompatImageView, color: Int) {
    imageView.setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY)
}

@BindingAdapter("drawableResource")
fun AppCompatImageView.setDrawableResource(@DrawableRes drawableResId: Int?) {
    drawableResId?.let { setImageResource(it) }
}
