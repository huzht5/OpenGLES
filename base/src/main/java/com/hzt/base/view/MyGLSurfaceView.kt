package com.hzt.base.view

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import com.hzt.base.common.MSAAConfigChooser

class MyGLSurfaceView(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs) {

    fun prepare(renderer: Renderer) {
        setEGLContextClientVersion(3)
        setEGLConfigChooser(MSAAConfigChooser())
        preserveEGLContextOnPause = true
        setRenderer(renderer)
        renderMode = RENDERMODE_WHEN_DIRTY
    }
}