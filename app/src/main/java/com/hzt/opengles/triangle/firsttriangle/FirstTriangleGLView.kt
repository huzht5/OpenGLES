package com.hzt.opengles.triangle.firsttriangle

import android.content.Context
import android.util.AttributeSet
import com.hzt.base.view.MyGLSurfaceView

class FirstTriangleGLView(context: Context?, attrs: AttributeSet?) : MyGLSurfaceView(context, attrs) {
    private val mRenderer = FirstTriangleRenderer()

    init {
        setRenderer(mRenderer)
        renderMode = RENDERMODE_WHEN_DIRTY
    }

    override fun onDestroy() {
        super.onDestroy()
        queueEvent { mRenderer.onDestroy() }
    }
}