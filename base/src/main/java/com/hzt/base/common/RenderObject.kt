package com.hzt.base.common

import android.opengl.GLES30
import com.hzt.base.mesh.Mesh

class RenderObject(shaderProgram: ShaderProgram, mesh: Mesh) {
    private var mShaderProgram = shaderProgram
    private var mMesh = mesh
    private val mVAO = IntArray(1)
    private val mVBO = IntArray(1)
    private val mEBO = IntArray(1)

    fun prepare() {
        mShaderProgram.createGlProgram()

        genObjects()
        updateArrayBuffer()
    }

    fun genObjects() {
        if (mVAO[0] == 0) {
            GLES30.glGenVertexArrays(1, mVAO, 0)
        }
        if (mVBO[0] == 0) {
            GLES30.glGenBuffers(1, mVBO, 0)
        }
        if (mEBO[0] == 0) {
            GLES30.glGenBuffers(1, mEBO, 0)
        }
    }

    fun updateArrayBuffer() {
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, mVBO[0])

        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER,
            mMesh.mVertexArray.size * Constants.BYTES_PER_FLOAT,
            null, GLES30.GL_STATIC_DRAW)
        GLES30.glBufferSubData(GLES30.GL_ARRAY_BUFFER, 0,
            mMesh.mVertexArray.size * Constants.BYTES_PER_FLOAT, mMesh.mVertexBuffer)

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0)
    }

    fun updates() {
        GLES30.glBindVertexArray(mVAO[0])
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, mEBO[0])

        GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER,
            mMesh.mIndexArray.size * Constants.BYTES_PER_INT,
            null, GLES30.GL_STATIC_DRAW)
        GLES30.glBufferSubData(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0,
            mMesh.mIndexArray.size * Constants.BYTES_PER_INT, mMesh.mIndexBuffer)

        GLES30.glEnableVertexAttribArray(0)
        val totalSize = Constants.VERTEX_COORDINATE_SIZE
        GLES30.glVertexAttribPointer(0, Constants.VERTEX_COORDINATE_SIZE, GLES30.GL_FLOAT,
            false, totalSize * Constants.BYTES_PER_FLOAT, 0)

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0)
        GLES30.glBindVertexArray(0)
    }
}