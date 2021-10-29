package com.hzt.base.common

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class Scene(renderObjectList: ArrayList<RenderObject>) : GLSurfaceView.Renderer {
    private val mRenderObjectList = renderObjectList

    companion object {
        private const val TAG = "Scene"
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        for (renderObject in mRenderObjectList) {
            renderObject.prepare()
        }
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {

    }

    override fun onDrawFrame(gl: GL10?) {

    }
}