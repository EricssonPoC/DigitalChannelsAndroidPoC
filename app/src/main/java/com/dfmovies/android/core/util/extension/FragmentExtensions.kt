package com.dfmovies.android.core.util.extension

import androidx.fragment.app.Fragment
import androidx.transition.Transition
import com.google.android.material.transition.MaterialSharedAxis

@Suppress("EmptyFunctionBlock")
fun Fragment.applySharedAxisXTransition(
    doOnStart: (() -> Unit)? = null,
    doOnEnd: (() -> Unit)? = null
) {
    val forward = MaterialSharedAxis(MaterialSharedAxis.X, true).addListener(object :
            Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                doOnEnd?.invoke()
            }

            override fun onTransitionResume(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionStart(transition: Transition) {
                doOnStart?.invoke()
            }
        })
    val backward = MaterialSharedAxis(MaterialSharedAxis.X, false).addListener(object :
            Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                doOnEnd?.invoke()
            }

            override fun onTransitionResume(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionStart(transition: Transition) {
                doOnStart?.invoke()
            }
        })
    reenterTransition = backward
    enterTransition = forward
    returnTransition = backward
}

@Suppress("EmptyFunctionBlock")
fun Fragment.applySharedAxisYTransition(
    doOnStart: (() -> Unit)? = null,
    doOnEnd: (() -> Unit)? = null
) {
    val forward = MaterialSharedAxis(MaterialSharedAxis.Y, true).addListener(object :
            Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                doOnEnd?.invoke()
            }

            override fun onTransitionResume(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionStart(transition: Transition) {
                doOnStart?.invoke()
            }
        })
    val backward = MaterialSharedAxis(MaterialSharedAxis.Y, false).addListener(object :
            Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                doOnEnd?.invoke()
            }

            override fun onTransitionResume(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionStart(transition: Transition) {
                doOnStart?.invoke()
            }
        })
    reenterTransition = backward
    enterTransition = forward
    returnTransition = backward
}

@Suppress("EmptyFunctionBlock")
fun Fragment.applySharedAxisZTransition(
    doOnStart: (() -> Unit)? = null,
    doOnEnd: (() -> Unit)? = null
) {
    val forward = MaterialSharedAxis(MaterialSharedAxis.Z, true).addListener(object :
            Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                doOnEnd?.invoke()
            }

            override fun onTransitionResume(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionStart(transition: Transition) {
                doOnStart?.invoke()
            }
        })
    val backward = MaterialSharedAxis(MaterialSharedAxis.Z, false).addListener(object :
            Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                doOnEnd?.invoke()
            }

            override fun onTransitionResume(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionStart(transition: Transition) {
                doOnStart?.invoke()
            }
        })
    reenterTransition = backward
    enterTransition = forward
    returnTransition = backward
    exitTransition = backward
}