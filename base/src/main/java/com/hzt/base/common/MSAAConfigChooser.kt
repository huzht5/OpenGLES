package com.hzt.base.common

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGL10
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.egl.EGLDisplay

class MSAAConfigChooser : GLSurfaceView.EGLConfigChooser {
    companion object {
        private const val EGL_LEVEL = 0
        private const val EGL_RENDERABLE_TYPE = 4
        private const val EGL_RED_SIZE = 8
        private const val EGL_GREEN_SIZE = 8
        private const val EGL_BLUE_SIZE = 8
        private const val EGL_ALPHA_SIZE = 8
        private const val EGL_DEPTH_SIZE = 24
        private const val EGL_SAMPLE_BUFFERS = 1
        private const val EGL_SAMPLES = 4
    }

    override fun chooseConfig(egl: EGL10?, display: EGLDisplay?): EGLConfig? {
        val attributes = intArrayOf(
            EGL10.EGL_LEVEL, EGL_LEVEL,
            EGL10.EGL_RENDERABLE_TYPE, EGL_RENDERABLE_TYPE,
            EGL10.EGL_COLOR_BUFFER_TYPE, EGL10.EGL_RGB_BUFFER,
            EGL10.EGL_RED_SIZE, EGL_RED_SIZE,
            EGL10.EGL_GREEN_SIZE, EGL_GREEN_SIZE,
            EGL10.EGL_BLUE_SIZE, EGL_BLUE_SIZE,
            EGL10.EGL_ALPHA_SIZE, EGL_ALPHA_SIZE,
            EGL10.EGL_DEPTH_SIZE, EGL_DEPTH_SIZE,
            EGL10.EGL_SAMPLE_BUFFERS, EGL_SAMPLE_BUFFERS,
            EGL10.EGL_SAMPLES, EGL_SAMPLES,
            EGL10.EGL_NONE
        )
        val configs = arrayOfNulls<EGLConfig>(1)
        val configCounts = IntArray(1)
        egl?.eglChooseConfig(display, attributes, configs, 1, configCounts)
        return if (configCounts[0] == 0) null else configs[0]
    }
}