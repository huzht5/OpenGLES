package com.hzt.base.common

import android.opengl.GLES32
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

abstract class Mesh {
    lateinit var mVertexArray: FloatArray
    lateinit var mVertexBuffer: FloatBuffer
    lateinit var mIndexBuffer: Buffer
    var mHasTextureCoordinates = false
    var mHasNormals = false
    var mIndexSize = 0
    var mBytes = 0
    var mType = 0
    private val mVAO = IntArray(1)
    private val mVBO = IntArray(1)
    private val mEBO = IntArray(1)

    fun onCreate() {
        if (mVAO[0] == 0) {
            GLES32.glGenVertexArrays(1, mVAO, 0)
            GLES32.glBindVertexArray(mVAO[0])

            if (mVBO[0] == 0) {
                GLES32.glGenBuffers(1, mVBO, 0)
            }
            GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, mVBO[0])
            GLES32.glBufferData(GLES32.GL_ARRAY_BUFFER, mVertexArray.size * Constants.BYTES_PER_FLOAT, null, GLES32.GL_STATIC_DRAW)
            GLES32.glBufferSubData(GLES32.GL_ARRAY_BUFFER, 0, mVertexArray.size * Constants.BYTES_PER_FLOAT, mVertexBuffer)

            if (mEBO[0] == 0) {
                GLES32.glGenBuffers(1, mEBO, 0)
            }
            GLES32.glBindBuffer(GLES32.GL_ELEMENT_ARRAY_BUFFER, mEBO[0])
            GLES32.glBufferData(GLES32.GL_ELEMENT_ARRAY_BUFFER, mIndexSize * mBytes, null, GLES32.GL_STATIC_DRAW)
            GLES32.glBufferSubData(GLES32.GL_ELEMENT_ARRAY_BUFFER, 0, mIndexSize * mBytes, mIndexBuffer)

            var totalSize = Constants.VERTEX_COORDINATE_SIZE
            if (mHasTextureCoordinates) {
                totalSize += Constants.TEXTURE_COORDINATE_SIZE
            }
            if (mHasNormals) {
                totalSize += Constants.NORMAL_VECTOR_SIZE
            }

            var index = 0
            var offset = 0
            GLES32.glEnableVertexAttribArray(index)
            GLES32.glVertexAttribPointer(index, Constants.VERTEX_COORDINATE_SIZE, GLES32.GL_FLOAT,
                false, totalSize * Constants.BYTES_PER_FLOAT, offset * Constants.BYTES_PER_FLOAT)
            offset += Constants.VERTEX_COORDINATE_SIZE

            if (mHasTextureCoordinates) {
                index++
                GLES32.glEnableVertexAttribArray(index)
                GLES32.glVertexAttribPointer(index, Constants.TEXTURE_COORDINATE_SIZE, GLES32.GL_FLOAT,
                    false, totalSize * Constants.BYTES_PER_FLOAT, offset * Constants.BYTES_PER_FLOAT)
                offset += Constants.TEXTURE_COORDINATE_SIZE
            }

            if (mHasNormals) {
                index++
                GLES32.glEnableVertexAttribArray(index)
                GLES32.glVertexAttribPointer(index, Constants.NORMAL_VECTOR_SIZE, GLES32.GL_FLOAT,
                    false, totalSize * Constants.BYTES_PER_FLOAT, offset * Constants.BYTES_PER_FLOAT)
            }

            GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, 0)
            GLES32.glBindVertexArray(0)
        }
    }

    fun updateVertex(vertex: FloatArray) {
        mVertexArray = vertex
        val vertexBuffer = ByteBuffer.allocateDirect(mVertexArray.size * Constants.BYTES_PER_FLOAT)
        vertexBuffer.order(ByteOrder.nativeOrder())
        mVertexBuffer = vertexBuffer.asFloatBuffer()
        mVertexBuffer.put(mVertexArray)
        mVertexBuffer.position(0)

        GLES32.glBindVertexArray(mVAO[0])

        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, mVBO[0])
        GLES32.glBufferData(GLES32.GL_ARRAY_BUFFER, mVertexArray.size * Constants.BYTES_PER_FLOAT, null, GLES32.GL_STATIC_DRAW)
        GLES32.glBufferSubData(GLES32.GL_ARRAY_BUFFER, 0, mVertexArray.size * Constants.BYTES_PER_FLOAT, mVertexBuffer)

        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, 0)
        GLES32.glBindVertexArray(0)
    }

    fun draw() {
        if (mVAO[0] != 0) {
            GLES32.glBindVertexArray(mVAO[0])
            GLES32.glDrawElements(GLES32.GL_TRIANGLES, mIndexSize, mType, 0)
            GLES32.glBindVertexArray(0)
        }
    }

    fun onDestroy() {
        if (mVBO[0] != 0) {
            GLES32.glDeleteBuffers(1, mVBO, 0)
            mVBO[0] = 0
        }
        if (mEBO[0] != 0) {
            GLES32.glDeleteBuffers(1, mEBO, 0)
            mEBO[0] = 0
        }
        if (mVAO[0] != 0) {
            GLES32.glDeleteVertexArrays(1, mVAO, 0)
            mVAO[0] = 0
        }
    }
}