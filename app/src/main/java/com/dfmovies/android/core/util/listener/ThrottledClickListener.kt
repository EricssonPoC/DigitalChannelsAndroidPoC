package com.dfmovies.android.core.util.listener

import android.view.View
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import timber.log.Timber

/**
 * View.OnClickListener implementation that receives the first clicked item,
 * and ignores the rest of it that received during threshold period.
 * Especially useful to use for components that unable to handle double taps,
 * like Android Navigation Components.
 */

@Suppress("MagicNumber")
abstract class ThrottledClickListener : View.OnClickListener {
    private val publishSubject: PublishSubject<View> = PublishSubject.create()
    private val thresholdInMilliseconds: Long = 700L

    abstract fun onClicked(view: View)

    override fun onClick(view: View?) {
        if (view != null) {
            publishSubject.onNext(view)
        }
    }

    init {
        publishSubject
            .throttleFirst(thresholdInMilliseconds, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { v ->
                    onClicked(v)
                },
                {
                    Timber.e("Unable to deliver click $it")
                }
            )
    }
}