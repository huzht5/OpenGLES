package com.hzt.base.mesh

import com.hzt.base.common.SimpleMesh

class Triangle : SimpleMesh(VERTEX_ARRAY, INDEX_ARRAY, false, false) {
    companion object {
        val VERTEX_ARRAY = floatArrayOf(
            -0.5F, -0.5F, 0.0F,
            0.5F, -0.5F, 0.0F,
            0.0F,  0.5F, 0.0F)
        val INDEX_ARRAY = byteArrayOf(
            0, 1, 2)
    }
}