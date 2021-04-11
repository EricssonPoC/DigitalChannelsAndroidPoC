package com.dfmovies.android.core.ui

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.trendyol.medusalib.navigator.Navigator
import dagger.android.AndroidInjection

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T

    lateinit var navigator: Navigator

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setThemeIfNeeded()
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    public override fun onResume() {
        super.onResume()
        if (trackPageViewAutomatically()) {
            trackPageView()
        }
    }

    open fun setThemeIfNeeded() {}

    open fun getScreeningPage(): String = "#" + this.javaClass.simpleName

    open fun trackPageViewAutomatically(): Boolean = true


    fun showShortToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showLongToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun setStatusBarColor(@ColorRes colorResId: Int) {
        window?.statusBarColor = ContextCompat.getColor(this, colorResId)
    }

    @Suppress("EmptyFunctionBlock")
    private fun trackPageView() {
    }

}