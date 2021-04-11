package com.dfmovies.android.core.di.module

import com.dfmovies.android.authentication.ui.AuthenticationActivity
import com.dfmovies.android.main.ui.MainActivity
import com.dfmovies.android.splash.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("TooManyFunctions")
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindAuthenticationActivity(): AuthenticationActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity


}