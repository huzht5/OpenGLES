package com.hzt.base.mesh

import com.hzt.base.common.Constants
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.IntBuffer

abstract class Mesh(vertex: FloatArray, index: IntArray) {
    var mVertexBuffer: FloatBuffer
    var mIndexBuffer: IntBuffer

    init {
        val vertexBuffer = ByteBuffer.allocateDirect(vertex.size * Constants.BYTES_PER_FLOAT)
        vertexBuffer.order(ByteOrder.nativeOrder())
        mVertexBuffer = vertexBuffer.asFloatBuffer()
        mVertexBuffer.put(vertex)
        mVertexBuffer.position(0)

        val indexBuffer = ByteBuffer.allocateDirect(index.size * Constants.BYTES_PER_INT)
        indexBuffer.order(ByteOrder.nativeOrder())
        mIndexBuffer = indexBuffer.asIntBuffer()
        mIndexBuffer.put(index)
        mIndexBuffer.position(0)
    }
}