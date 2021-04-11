package com.dfmovies.android.authentication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dfmovies.android.R
import com.dfmovies.android.authentication.ui.login.LoginFragment
import com.dfmovies.android.core.ui.BaseActivity
import com.dfmovies.android.core.util.extension.canChildHandleNavigation
import com.dfmovies.android.core.util.extension.getNavigateChildFragment
import com.dfmovies.android.databinding.ActivityAuthenticationBinding
import com.trendyol.medusalib.navigator.MultipleStackNavigator
import com.trendyol.medusalib.navigator.Navigator
import com.trendyol.medusalib.navigator.NavigatorConfiguration
import com.trendyol.medusalib.navigator.transaction.NavigatorTransaction

class AuthenticationActivity :
    BaseActivity<ActivityAuthenticationBinding>(),
    Navigator.NavigatorListener {

    override fun getLayoutId(): Int = R.layout.activity_authentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpNavigator(savedInstanceState)

        setUpViewModel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        navigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        with(navigator) {
            when {
                canChildHandleNavigation() -> getNavigateChildFragment().goBack()
                canGoBack() -> goBack()
                else -> super.onBackPressed()
            }
        }
    }

    override fun onTabChanged(tabIndex: Int) {
    }

    private fun setUpViewModel() {

    }

    private fun setUpNavigator(savedInstanceState: Bundle? = null) {
        navigator = createNavigator()
        navigator.initialize(savedInstanceState)
    }

    private fun createNavigator(): Navigator {
        return MultipleStackNavigator(
            fragmentManager = supportFragmentManager,
            containerId = R.id.authenticationFragmentsContainer,
            rootFragmentProvider = getRootFragments().map { { it } },
            navigatorListener = this,
            navigatorConfiguration = NavigatorConfiguration(
                initialTabIndex = 0,
                alwaysExitFromInitial = true,
                defaultNavigatorTransaction = NavigatorTransaction.ATTACH_DETACH
            )
        )

    }

    private fun getRootFragments(): List<Fragment> =
        listOf(
            LoginFragment.newInstance()
        )


    companion object {
        @JvmStatic
        fun newIntent(context: Context): Intent {
            return Intent(context, AuthenticationActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
        }
    }
}