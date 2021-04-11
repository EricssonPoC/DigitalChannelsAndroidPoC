package com.dfmovies.android.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.dfmovies.android.R
import com.dfmovies.android.core.ui.BaseActivity
import com.dfmovies.android.core.util.extension.canChildHandleNavigation
import com.dfmovies.android.core.util.extension.getNavigateChildFragment
import com.dfmovies.android.core.util.extension.hide
import com.dfmovies.android.core.util.extension.visible
import com.dfmovies.android.databinding.ActivityMainBinding
import com.dfmovies.android.main.ui.discover.DiscoverMovieFragment
import com.dfmovies.android.main.ui.favorite.FavoriteFragment
import com.dfmovies.android.main.ui.search.SearchFragment
import com.dfmovies.android.main.ui.watchlist.WatchlistFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trendyol.medusalib.navigator.MultipleStackNavigator
import com.trendyol.medusalib.navigator.Navigator
import com.trendyol.medusalib.navigator.NavigatorConfiguration
import com.trendyol.medusalib.navigator.transaction.NavigatorTransaction

class MainActivity :
        BaseActivity<ActivityMainBinding>(),
        BottomNavigationView.OnNavigationItemSelectedListener,
        Navigator.NavigatorListener {


    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator = createNavigator()
        navigator.initialize(savedInstanceState)

        setUpView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        navigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    private fun setUpView() {
        with(binding) {
            bottomNavigationView.setOnNavigationItemSelectedListener(this@MainActivity)

        }
    }

    private fun createNavigator(): Navigator =
            MultipleStackNavigator(
                    fragmentManager = supportFragmentManager,
                    containerId = R.id.dashboardMainContainer,
                    rootFragmentProvider = getRootFragments().map { { it } },
                    navigatorListener = this,
                    navigatorConfiguration = NavigatorConfiguration(
                            initialTabIndex = 0,
                            alwaysExitFromInitial = true,
                            defaultNavigatorTransaction = NavigatorTransaction.SHOW_HIDE
                    )
            )

    override fun onBackPressed() {
        with(navigator) {
            when {
                onBackPressedDispatcher.hasEnabledCallbacks() -> onBackPressedDispatcher.onBackPressed()
                canChildHandleNavigation() -> getNavigateChildFragment().goBack()
                canGoBack() -> goBack()
                else -> super.onBackPressed()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (binding.bottomNavigationView.selectedItemId != item.itemId) {
            return when (item.itemId) {
                R.id.bottomNavSearch -> {
                    switchTab(MainTabs.DISCOVER.index)
                    true
                }
                R.id.bottomNavWatchlist -> {
                    switchTab(MainTabs.WATCHLIST.index)
                    true
                }
                R.id.bottomNavFavorite -> {
                    switchTab(MainTabs.FAVORITE.index)
                    true
                }
                else -> {
                    false
                }
            }
        }
        return false
    }

    fun switchTab(index: Int) {
        navigator.switchTab(index)
    }

    private fun getRootFragments(): List<Fragment> =
            listOf(
                    DiscoverMovieFragment.newInstance(),
                    WatchlistFragment.newInstance(),
                    FavoriteFragment.newInstance()
            )

    override fun onTabChanged(tabIndex: Int) {
        when (tabIndex) {
            MainTabs.DISCOVER.index -> {
                binding.bottomNavigationView.selectedItemId = R.id.bottomNavSearch
            }
            MainTabs.WATCHLIST.index -> {
                binding.bottomNavigationView.selectedItemId = R.id.bottomNavWatchlist
            }
            MainTabs.FAVORITE.index -> {
                binding.bottomNavigationView.selectedItemId = R.id.bottomNavFavorite
            }
        }
    }

    fun hideBottomNavigation() = binding.bottomNavigationView.hide()

    fun showBottomNavigation() = binding.bottomNavigationView.visible()

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
                    .apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
        }
    }
}