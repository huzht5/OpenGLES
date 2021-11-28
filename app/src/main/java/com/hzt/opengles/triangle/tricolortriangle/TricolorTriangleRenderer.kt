package com.hzt.opengles.triangle.tricolortriangle

import android.opengl.GLES32
import com.hzt.base.common.MyRenderer
import com.hzt.base.common.ShaderProgram
import com.hzt.base.common.SimpleMesh
import com.hzt.base.util.LogUtil
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class TricolorTriangleRenderer : MyRenderer() {
    companion object {
        private const val TAG = "TricolorTriangleRenderer"
        private val UP_COLOR = floatArrayOf(0.0F, 0.0F, 1.0F)
        private val LEFT_COLOR = floatArrayOf(0.0F, 1.0F, 0.0F)
        private val RIGHT_COLOR = floatArrayOf(1.0F, 0.0F, 0.0F)
    }
    val mUpColor = UP_COLOR
    val mLeftColor = LEFT_COLOR
    val mRightColor = RIGHT_COLOR
    private val mShaderProgram = ShaderProgram("shader/triangle/tricolor_triangle/tricolor_triangle.vs",
        "shader/triangle/tricolor_triangle/tricolor_triangle.fs")
    private val mTriangleMesh = TricolorTriangle()

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
        LogUtil.d(TAG, "[onDrawFrame]")
        mShaderProgram.use()
        mTriangleMesh.draw()
    }

    fun update() {
        LogUtil.d(TAG, "[update]")
        mTriangleMesh.updateVertex(floatArrayOf(
            -0.5F, -0.5F, 0.0F, mLeftColor[0], mLeftColor[1], mLeftColor[2],
            0.5F, -0.5F, 0.0F, mRightColor[0], mRightColor[1], mRightColor[2],
            0.0F,  0.5F, 0.0F, mUpColor[0], mUpColor[1], mUpColor[2]))
    }

    override fun onDestroy() {
        super.onDestroy()
        mShaderProgram.onDestroy()
        mTriangleMesh.onDestroy()
    }

    class TricolorTriangle : SimpleMesh(VERTEX_ARRAY, INDEX_ARRAY, false, true) {
        companion object {
            val VERTEX_ARRAY = floatArrayOf(
                -0.5F, -0.5F, 0.0F, LEFT_COLOR[0], LEFT_COLOR[1], LEFT_COLOR[2],
                0.5F, -0.5F, 0.0F, RIGHT_COLOR[0], RIGHT_COLOR[1], RIGHT_COLOR[2],
                0.0F,  0.5F, 0.0F, UP_COLOR[0], UP_COLOR[1], UP_COLOR[2])
            val INDEX_ARRAY = byteArrayOf(
                0, 1, 2)
        }
    }
}