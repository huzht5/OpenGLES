package com.hzt.opengles.triangle

import android.opengl.GLES30
import android.opengl.GLSurfaceView
import com.hzt.base.common.Constants
import com.hzt.base.mesh.TriangleMesh
import com.hzt.base.common.ShaderProgram
import com.hzt.base.util.LogUtil
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class TriangleRenderer : GLSurfaceView.Renderer {
    private lateinit var mShaderProgram: ShaderProgram
    private val mVAO = IntArray(1)
    private val mVBO = IntArray(1)
    private val mEBO = IntArray(1)

    companion object {
        private const val TAG = "TriangleRenderer"
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        LogUtil.d(TAG, "[onSurfaceCreated]")
        val triangleMesh = TriangleMesh()

        //mShaderProgram.use()

        if (mVAO[0] == 0) {
            GLES30.glGenVertexArrays(1, mVAO, 0)
        }
        if (mVBO[0] == 0) {
            GLES30.glGenBuffers(1, mVBO, 0)
        }
        if (mEBO[0] == 0) {
            GLES30.glGenBuffers(1, mEBO, 0)
        }

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, mVBO[0])
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER,
            TriangleMesh.VERTEX_ARRAY.size * Constants.BYTES_PER_FLOAT,
            null, GLES30.GL_STATIC_DRAW)
        GLES30.glBufferSubData(GLES30.GL_ARRAY_BUFFER, 0,
            TriangleMesh.VERTEX_ARRAY.size * Constants.BYTES_PER_FLOAT, triangleMesh.mVertexBuffer)

        GLES30.glBindVertexArray(mVAO[0])
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, mEBO[0])
        GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER,
            TriangleMesh.INDEX_ARRAY.size * Constants.BYTES_PER_INT,
            null, GLES30.GL_STATIC_DRAW)
        GLES30.glBufferSubData(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0,
            TriangleMesh.INDEX_ARRAY.size * Constants.BYTES_PER_INT, triangleMesh.mIndexBuffer)

        GLES30.glEnableVertexAttribArray(0)
        val totalSize = Constants.VERTEX_COORDINATE_SIZE
        GLES30.glVertexAttribPointer(0, Constants.VERTEX_COORDINATE_SIZE, GLES30.GL_FLOAT,
            false, totalSize * Constants.BYTES_PER_FLOAT, 0)

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0)
        GLES30.glBindVertexArray(0)

        mShaderProgram = ShaderProgram("shader/triangle/vertex.glsl", "shader/triangle/fragment.glsl")
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        LogUtil.d(TAG, "[onSurfaceChanged]")
        GLES30.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES30.glEnable(GLES30.GL_CULL_FACE)
        GLES30.glClearColor(0.0F, 0.0F, 0.0F, 1.0F)
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT or GLES30.GL_DEPTH_BUFFER_BIT)

        mShaderProgram.use()
        GLES30.glBindVertexArray(mVAO[0])
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, TriangleMesh.INDEX_ARRAY.size, GLES30.GL_UNSIGNED_INT, 0)
        GLES30.glBindVertexArray(0)
    }
}