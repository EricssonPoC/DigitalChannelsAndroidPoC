package com.dfmovies.android.splash.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dfmovies.android.R
import com.dfmovies.android.authentication.ui.AuthenticationActivity
import com.dfmovies.android.core.ui.BaseActivity
import com.dfmovies.android.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
        setUpViewModel()
    }

    private fun setUpView() {
        with(binding) {
            root.postDelayed({
                startActivity(AuthenticationActivity.newIntent(this@SplashActivity))
            }, 2000)
        }
    }

    private fun setUpViewModel() {
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SplashActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }
}
