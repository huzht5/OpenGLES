package com.hzt.base.common

import android.opengl.GLES30
import com.hzt.base.util.FileUtil
import com.hzt.base.util.LogUtil

class ShaderProgram(vertexSource: String, fragmentSource: String) {
    private val mVertexSource = vertexSource
    private val mFragmentSource = fragmentSource
    var mProgramId = 0

    companion object {
        private const val TAG = "ShaderProgram"
    }

    fun createGlProgram() {
        mProgramId = createGlProgram(mVertexSource, mFragmentSource)
    }

    fun use() = GLES30.glUseProgram(mProgramId)

    fun getAttributeLocation(name: String) =
        GLES30.glGetAttribLocation(mProgramId, name)

    fun setInt(name: String, value: Int) =
        GLES30.glUniform1i(GLES30.glGetUniformLocation(mProgramId, name), value)

    fun setFloat(name: String, value: Float) =
        GLES30.glUniform1f(GLES30.glGetUniformLocation(mProgramId, name), value)

    fun setVec2(name: String, value: FloatArray) =
        GLES30.glUniform2fv(GLES30.glGetUniformLocation(mProgramId, name), 1, value, 0)

    fun setVec3(name: String, value: FloatArray) =
        GLES30.glUniform3fv(GLES30.glGetUniformLocation(mProgramId, name), 1, value, 0)

    fun setVec4(name: String, value: FloatArray) =
        GLES30.glUniform4fv(GLES30.glGetUniformLocation(mProgramId, name), 1, value, 0)

    fun setMat2(name: String, value: FloatArray) =
        GLES30.glUniformMatrix2fv(GLES30.glGetUniformLocation(mProgramId, name), 1, false, value, 0)

    fun setMat3(name: String, value: FloatArray) =
        GLES30.glUniformMatrix3fv(GLES30.glGetUniformLocation(mProgramId, name), 1, false, value, 0)

    fun setMat4(name: String, value: FloatArray) =
        GLES30.glUniformMatrix4fv(GLES30.glGetUniformLocation(mProgramId, name), 1, false, value, 0)

    private fun createGlProgram(vertexSource: String?, fragmentSource: String?): Int {
        val vertexShader = loadShader(GLES30.GL_VERTEX_SHADER, vertexSource)
        if (vertexShader == 0) {
            LogUtil.e(TAG, "[createGlProgram] vertexShader = 0")
            return 0
        }
        val fragmentShader = loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentSource)
        if (fragmentShader == 0){
            LogUtil.e(TAG, "[createGlProgram] fragmentShader = 0")
            return 0
        }
        var program = GLES30.glCreateProgram()
        if (program == 0) {
            LogUtil.e(TAG, "[createGlProgram] program = 0")
            return 0
        }
        GLES30.glAttachShader(program, vertexShader)
        GLES30.glAttachShader(program, fragmentShader)
        GLES30.glLinkProgram(program)
        val linkStatus = IntArray(1)
        GLES30.glGetProgramiv(program, GLES30.GL_LINK_STATUS, linkStatus, 0)
        if (linkStatus[0] != GLES30.GL_TRUE) {
            LogUtil.e(TAG, "[createGlProgram] could not link program: " + GLES30.glGetProgramInfoLog(program))
            GLES30.glDeleteProgram(program)
            program = 0
        }
        GLES30.glDeleteShader(vertexShader)
        GLES30.glDeleteShader(fragmentShader)
        return program
    }

    private fun loadShader(shaderType: Int, source: String?): Int {
        var shader = GLES30.glCreateShader(shaderType)
        if (shader == 0) {
            LogUtil.e(TAG, "[loadShader] shader = 0")
            return 0
        }
        GLES30.glShaderSource(shader, source)
        GLES30.glCompileShader(shader)
        val compiled = IntArray(1)
        GLES30.glGetShaderiv(shader, GLES30.GL_COMPILE_STATUS, compiled, 0)
        if (compiled[0] == 0) {
            LogUtil.e(TAG, "[loadShader] could not compile shader: $shaderType \n" + GLES30.glGetShaderInfoLog(shader))
            GLES30.glDeleteShader(shader)
            shader = 0
        }
        return shader
    }
}