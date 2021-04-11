package com.dfmovies.android.core.util.image

import com.bumptech.glide.request.RequestOptions

class RequestOptionsFactory {

    companion object {
        private val noRequestOptions: RequestOptions = RequestOptions()

        private val circleCropImageRequestOptions: RequestOptions = RequestOptions().circleCrop()

        private val requestOptions: MutableMap<ImageViewType, RequestOptions> = mutableMapOf(
            ImageViewType.NO_TYPE to noRequestOptions,
            ImageViewType.CIRCLE_CROP to circleCropImageRequestOptions
        )

        @JvmStatic
        @JvmOverloads
        fun create(imageViewType: ImageViewType? = ImageViewType.DEFAULT): RequestOptions =
            requestOptions[imageViewType] ?: noRequestOptions
    }
}