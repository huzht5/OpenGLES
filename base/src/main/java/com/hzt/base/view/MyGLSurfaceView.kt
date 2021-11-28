package com.hzt.base.view

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import com.hzt.base.common.MSAAConfigChooser
import com.hzt.base.util.LogUtil
import kotlin.math.min

abstract class MyGLSurfaceView(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs) {

    init {
        setEGLContextClientVersion(3)
        setEGLConfigChooser(MSAAConfigChooser())
        preserveEGLContextOnPause = true
    }

    final override fun setEGLContextClientVersion(version: Int) {
        super.setEGLContextClientVersion(version)
    }

    final override fun setEGLConfigChooser(configChooser: EGLConfigChooser) {
        super.setEGLConfigChooser(configChooser)
    }

    final override fun setRenderer(renderer: Renderer) {
        super.setRenderer(renderer)
    }

    final override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val min = min(width, height)
        setMeasuredDimension(min, min)
    }

    open fun onDestroy() {
        LogUtil.d(javaClass.simpleName, "[onDestroy]")
    }
}