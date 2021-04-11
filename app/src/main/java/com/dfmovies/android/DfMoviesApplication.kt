package com.dfmovies.android

import android.content.Context
import com.dfmovies.android.core.di.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DfMoviesApplication: DaggerApplication() {

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        initTreeTenABP()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    private fun initTreeTenABP() {
        AndroidThreeTen.init(this)
    }

    companion object {
        lateinit var INSTANCE: DfMoviesApplication
            private set

        val applicationContext: Context
            get() {
                return INSTANCE.applicationContext
            }
    }

}