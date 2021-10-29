package com.hzt.base.mesh

import com.hzt.base.common.Constants
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.IntBuffer

abstract class Mesh(vertex: FloatArray, index: IntArray) {
    var mVertexArray: FloatArray = vertex
    var mIndexArray: IntArray = index
    var mVertexBuffer: FloatBuffer
    var mIndexBuffer: IntBuffer

    init {
        val vertexBuffer = ByteBuffer.allocateDirect(mVertexArray.size * Constants.BYTES_PER_FLOAT)
        vertexBuffer.order(ByteOrder.nativeOrder())
        mVertexBuffer = vertexBuffer.asFloatBuffer()
        mVertexBuffer.put(mVertexArray)
        mVertexBuffer.position(0)

        val indexBuffer = ByteBuffer.allocateDirect(mIndexArray.size * Constants.BYTES_PER_INT)
        indexBuffer.order(ByteOrder.nativeOrder())
        mIndexBuffer = indexBuffer.asIntBuffer()
        mIndexBuffer.put(mIndexArray)
        mIndexBuffer.position(0)
    }
}