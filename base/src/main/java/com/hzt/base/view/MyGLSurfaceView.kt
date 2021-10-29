package com.hzt.base.view

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import com.hzt.base.common.MSAAConfigChooser
import com.hzt.base.common.Scene

class MyGLSurfaceView(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs) {
    lateinit var mScene: Scene

    fun prepare(scene: Scene) {
        mScene = scene
        setEGLContextClientVersion(3)
        setEGLConfigChooser(MSAAConfigChooser())
        preserveEGLContextOnPause = true
        setRenderer(mScene)
        renderMode = RENDERMODE_WHEN_DIRTY
    }
}