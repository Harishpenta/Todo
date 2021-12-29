package com.curious.todo.utils

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar


class ProgressBarAnimation(
    private val progressBar: ProgressBar? = null,
    private val from: Float = 0f,
    private val to: Float = 0f
) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val value = from + (to - from) * interpolatedTime
        progressBar!!.progress = value.toInt()
    }

}