package com.dfmovies.android.core.util.extension

import com.dfmovies.android.core.util.navigator.HasNavigationInterceptor
import com.trendyol.medusalib.navigator.Navigator

internal fun Navigator.canChildHandleNavigation(): Boolean =
    (getCurrentFragment() as? HasNavigationInterceptor)?.canGoBack() == true

internal fun Navigator.getNavigateChildFragment(): HasNavigationInterceptor =
    getCurrentFragment() as HasNavigationInterceptor
