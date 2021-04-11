package com.dfmovies.android.core.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dfmovies.android.core.util.extension.inflate
import com.dfmovies.android.main.ui.MainActivity
import com.trendyol.medusalib.navigator.Navigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var binding: T

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    protected open fun canNavigate(): Boolean = fragmentNavigator != null

    protected val fragmentNavigator: Navigator?
        get() = (activity as? BaseActivity<*>)?.navigator

    fun getActivityViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, viewModelProviderFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        container
            .inflate<T>(getLayoutId(), false)
            .also { binding = it }
            .root

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        if (trackPageViewAutomatically()) {
            trackPageView()
        }
    }

    override fun onDestroyView() {
        (activity as? AppCompatActivity)?.setSupportActionBar(null)
        super.onDestroyView()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getScreeningPage(): String

    open fun trackPageViewAutomatically(): Boolean = true

    fun goBackToParentFragment() {
        fragmentNavigator?.reset(0, false)
    }

    fun startFragment(fragment: Fragment) {
        if (canNavigate()) {
            fragmentNavigator?.start(fragment)
        }
    }

    fun startFragment(fragment: Fragment, tabIndex: Int) {
        if (canNavigate()) {
            fragmentNavigator?.start(fragment, tabIndex)
        }
    }

    fun startFragment(fragment: Fragment, groupName: String) {
        if (canNavigate()) {
            fragmentNavigator?.start(fragment, groupName)
        }
    }

    fun startFragment(fragment: Fragment, tabIndex: Int, groupName: String) {
        if (canNavigate()) {
            fragmentNavigator?.start(fragment, tabIndex, groupName)
        }
    }

    fun clearGroup(groupName: String) {
        fragmentNavigator?.clearGroup(groupName)
    }


    fun showShortToastMessage(message: String) {
        (activity as? AppCompatActivity)?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun showLongToastMessage(message: String) {
        (activity as? AppCompatActivity)?.let {
            Toast.makeText(it, message, Toast.LENGTH_LONG).show()
        }
    }

    @Suppress("EmptyFunctionBlock")
    fun trackPageView() {
    }

    open fun onBackPressed(): Unit = activity?.onBackPressed() ?: Unit

    private fun hideStatusBar() {
        (activity as? AppCompatActivity)?.window?.addFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun showStatusBar() {
        (activity as? AppCompatActivity)?.window?.clearFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        (activity as? AppCompatActivity)?.window?.clearFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
        (activity as? AppCompatActivity)?.window?.addFlags(
            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        )
    }

    private fun transparentStatusBar() {
        (activity as? AppCompatActivity)?.window?.addFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
        (activity as? AppCompatActivity)?.window?.clearFlags(
            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        )
        (activity as? AppCompatActivity)?.window?.statusBarColor = Color.TRANSPARENT
    }

    open fun setStatusBarColor(color: Int) {
        (activity as? AppCompatActivity)?.window?.statusBarColor = color
    }

    open fun hideBottomNavigation(): Unit? {
        return (activity as? MainActivity)?.hideBottomNavigation()
    }

    open fun showBottomNavigation(): Unit? {
        return (activity as? MainActivity)?.showBottomNavigation()
    }
}