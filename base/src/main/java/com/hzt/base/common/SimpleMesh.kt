package com.hzt.base.common

import android.opengl.GLES32
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.IntBuffer
import java.nio.ShortBuffer

abstract class SimpleMesh private constructor(vertex: FloatArray, hasTextureCoordinates: Boolean, hasNormals: Boolean) : Mesh() {

    init {
        mVertexArray = vertex
        mHasTextureCoordinates = hasTextureCoordinates
        mHasNormals = hasNormals
        val vertexBuffer = ByteBuffer.allocateDirect(mVertexArray.size * Constants.BYTES_PER_FLOAT)
        vertexBuffer.order(ByteOrder.nativeOrder())
        mVertexBuffer = vertexBuffer.asFloatBuffer()
        mVertexBuffer.put(mVertexArray)
        mVertexBuffer.position(0)
    }

    constructor(vertex: FloatArray, index: ByteArray, hasTextureCoordinates: Boolean, hasNormals: Boolean)
            : this(vertex, hasTextureCoordinates, hasNormals) {
        mIndexSize = index.size
        mBytes = Constants.BYTES_PER_BYTE
        mType = GLES32.GL_UNSIGNED_BYTE
        mIndexBuffer = ByteBuffer.allocateDirect(mIndexSize * mBytes)
        (mIndexBuffer as ByteBuffer).order(ByteOrder.nativeOrder())
        (mIndexBuffer as ByteBuffer).put(index)
        mIndexBuffer.position(0)
    }

    constructor(vertex: FloatArray, index: ShortArray, hasTextureCoordinates: Boolean, hasNormals: Boolean)
            : this(vertex, hasTextureCoordinates, hasNormals) {
        mIndexSize = index.size
        mBytes = Constants.BYTES_PER_SHORT
        mType = GLES32.GL_UNSIGNED_SHORT
        val indexBuffer = ByteBuffer.allocateDirect(mIndexSize * mBytes)
        indexBuffer.order(ByteOrder.nativeOrder())
        mIndexBuffer = indexBuffer.asShortBuffer()
        (mIndexBuffer as ShortBuffer).put(index)
        mIndexBuffer.position(0)
    }

    constructor(vertex: FloatArray, index: IntArray, hasTextureCoordinates: Boolean, hasNormals: Boolean)
            : this(vertex, hasTextureCoordinates, hasNormals) {
        mIndexSize = index.size
        mBytes = Constants.BYTES_PER_INT
        mType = GLES32.GL_UNSIGNED_INT
        val indexBuffer = ByteBuffer.allocateDirect(mIndexSize * mBytes)
        indexBuffer.order(ByteOrder.nativeOrder())
        mIndexBuffer = indexBuffer.asIntBuffer()
        (mIndexBuffer as IntBuffer).put(index)
        mIndexBuffer.position(0)
    }
}