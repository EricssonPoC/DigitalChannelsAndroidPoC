package com.dfmovies.android.core.util.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.dfmovies.android.R
import com.dfmovies.android.core.util.extension.inflate
import com.dfmovies.android.core.util.extension.setThrottledClickListener
import com.dfmovies.android.databinding.ViewToolbarDynamicBinding
import com.google.android.material.appbar.MaterialToolbar

class DynamicToolbarView : FrameLayout {

    private val binding: ViewToolbarDynamicBinding = inflate(R.layout.view_toolbar_dynamic)

    var backButtonClickListener: (() -> Unit)? = null

    var actionTextClickListener: (() -> Unit)? = null

    var actionButtonClickListener: (() -> Unit)? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        with(binding) {
            imageViewBackButton.setThrottledClickListener {
                backButtonClickListener?.invoke()
            }
        }
    }

    fun setViewState(dynamicToolbarViewState: DynamicToolbarViewState?) {
        dynamicToolbarViewState?.let {
            binding.viewState = it
            binding.executePendingBindings()
        }
    }

    fun setToolbarTitle(title: String) {
        binding.viewState?.let { viewState ->
            setViewState(viewState.copy(title = title))
        }
    }

}