package com.dfmovies.android.core.util.navigator

/**
 * Interface for fragments using its own
 * child fragment manager. Those fragments can use
 * this interface and handle its own logic.
 */
interface HasNavigationInterceptor {

    fun goBack()

    fun canGoBack(): Boolean
}
