package com.hzt.opengles.triangle.singlecolortriangle

import android.opengl.GLES32
import com.hzt.base.common.MyRenderer
import com.hzt.base.common.ShaderProgram
import com.hzt.base.mesh.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class SingleColorTriangleRenderer : MyRenderer() {
    val mColor = floatArrayOf(0.0F, 1.0F, 0.0F)
    private val mShaderProgram = ShaderProgram("shader/triangle/single_color_triangle/single_color_triangle.vs",
        "shader/triangle/single_color_triangle/single_color_triangle.fs")
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
        mShaderProgram.setVec3("uColor", mColor)
        mTriangleMesh.draw()
    }

    override fun onDestroy() {
        super.onDestroy()
        mShaderProgram.onDestroy()
        mTriangleMesh.onDestroy()
    }
}