package com.hzt.base.common

import android.opengl.GLES32
import android.opengl.GLSurfaceView
import com.hzt.base.util.LogUtil
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

abstract class MyRenderer : GLSurfaceView.Renderer {
    var mWidth = 0
    var mHeight = 0

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        LogUtil.d(javaClass.simpleName, "[onSurfaceCreated]")
        GLES32.glEnable(GLES32.GL_CULL_FACE)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        LogUtil.d(javaClass.simpleName, "[onSurfaceChanged] width = $width, height = $height")
        mWidth = width
        mHeight = height
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT or GLES32.GL_DEPTH_BUFFER_BIT)
    }

    open fun onDestroy() {
        LogUtil.d(javaClass.simpleName, "[onDestroy]")
    }
}