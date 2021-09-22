package com.hzt.base.mesh

class TriangleMesh : Mesh(VERTEX_ARRAY, INDEX_ARRAY) {

    companion object {
        val VERTEX_ARRAY = floatArrayOf(
            0.5F, 0.5F, 0.0F,
            -0.5F, 0.5F, 0.0F,
            0.5F, -0.5F, 0.0F)
        val INDEX_ARRAY = intArrayOf(
            0, 1, 2)
    }
}