package com.hzt.opengles.triangle.firsttriangle

import android.opengl.GLES32
import com.hzt.base.common.MyRenderer
import com.hzt.base.common.ShaderProgram
import com.hzt.base.mesh.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class FirstTriangleRenderer : MyRenderer() {
    private val mShaderProgram = ShaderProgram("shader/triangle/first_triangle/first_triangle.vs",
        "shader/triangle/first_triangle/first_triangle.fs")
    private val mTriangleMesh = Triangle()

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        mShaderProgram.onCreate()
        mTriangleMesh.onCreate()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        GLES32.glViewport(0, 0, mWidth, mHeight)
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        mShaderProgram.use()
        mTriangleMesh.draw()
    }

    override fun onDestroy() {
        super.onDestroy()
        mShaderProgram.onDestroy()
        mTriangleMesh.onDestroy()
    }
}