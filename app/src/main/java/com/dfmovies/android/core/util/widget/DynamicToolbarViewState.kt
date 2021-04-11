package com.dfmovies.android.core.util.widget

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.dfmovies.android.R

data class DynamicToolbarViewState(
    @DrawableRes val icon: Int = R.drawable.ic_back,
    @ColorRes val iconTint: Int? = R.color.color_blue_grey_dark,
    val leftIconVisibility: Boolean = false,
    val title: String = "",
    val titleTextStyle: Int = R.style.PrimaryText_Regular_16,
)