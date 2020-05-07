package com.sonhvp.utilities.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator

fun View.visible() { this.visibility = View.VISIBLE }
fun View.invisible() { this.visibility = View.INVISIBLE }
fun View.gone() { this.visibility = View.GONE }
fun View.disappear(duration: Long = 200, block: ObjectAnimator.() -> Unit) {
    ObjectAnimator.ofFloat(this, "alpha", 1f, 0f).apply {
        interpolator = LinearInterpolator()
        repeatCount = 0
        setDuration(duration)
    }.apply(block).start()
}

fun View.appear(duration: Long = 200, block: ObjectAnimator.() -> Unit) {
    ObjectAnimator.ofFloat(this, "alpha", 0f, 1f).apply {
        interpolator = LinearInterpolator()
        repeatCount = 0
        setDuration(duration)
    }.apply(block).start()
}

fun ObjectAnimator.whenEnd(block: () -> Unit) {
    this.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) { }
        override fun onAnimationCancel(animation: Animator?) { }
        override fun onAnimationStart(animation: Animator?) { }
        override fun onAnimationEnd(animation: Animator?) { block() }
    })
}