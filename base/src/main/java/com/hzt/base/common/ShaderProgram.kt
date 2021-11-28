package com.hzt.base.common

import android.opengl.GLES32
import com.hzt.base.util.FileUtil
import com.hzt.base.util.LogUtil

class ShaderProgram(vertexAssetsPath: String, fragmentAssetsPath: String) {
    companion object {
        private const val TAG = "ShaderProgram"
    }
    private val mVertexAssetsPath = vertexAssetsPath
    private val mFragmentAssetsPath = fragmentAssetsPath
    private var mProgramId = 0

    fun onCreate() {
        if (mProgramId == 0) {
            mProgramId = createGlProgram(FileUtil.getTextFromAssets(mVertexAssetsPath), FileUtil.getTextFromAssets(mFragmentAssetsPath))
        }
    }

    fun use() {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[use] $this mProgramId is 0")
            return
        }
        GLES32.glUseProgram(mProgramId)
    }

    fun setInt(name: String, value: Int) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setInt] $this mProgramId is 0")
            return
        }
        GLES32.glUniform1i(GLES32.glGetUniformLocation(mProgramId, name), value)
    }

    fun setFloat(name: String, value: Float) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setFloat] $this mProgramId is 0")
            return
        }
        GLES32.glUniform1f(GLES32.glGetUniformLocation(mProgramId, name), value)
    }

    fun setVec2(name: String, value: FloatArray) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setVec2] $this mProgramId is 0")
            return
        }
        GLES32.glUniform2fv(GLES32.glGetUniformLocation(mProgramId, name), 1, value, 0)
    }

    fun setVec2(name: String, f1: Float, f2: Float) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setVec2] $this mProgramId is 0")
            return
        }
        GLES32.glUniform2fv(GLES32.glGetUniformLocation(mProgramId, name), 1, floatArrayOf(f1, f2), 0)
    }

    fun setVec3(name: String, value: FloatArray) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setVec3] $this mProgramId is 0")
            return
        }
        GLES32.glUniform3fv(GLES32.glGetUniformLocation(mProgramId, name), 1, value, 0)
    }

    fun setVec3(name: String, f1: Float, f2: Float, f3: Float) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setVec3] $this mProgramId is 0")
            return
        }
        GLES32.glUniform3fv(GLES32.glGetUniformLocation(mProgramId, name), 1, floatArrayOf(f1, f2, f3), 0)
    }

    fun setVec4(name: String, value: FloatArray) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setVec4] $this mProgramId is 0")
            return
        }
        GLES32.glUniform4fv(GLES32.glGetUniformLocation(mProgramId, name), 1, value, 0)
    }

    fun setVec4(name: String, f1: Float, f2: Float, f3: Float, f4: Float) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setVec4] $this mProgramId is 0")
            return
        }
        GLES32.glUniform4fv(GLES32.glGetUniformLocation(mProgramId, name), 1, floatArrayOf(f1, f2, f3, f4), 0)
    }

    fun setMat2(name: String, value: FloatArray) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setMat2] $this mProgramId is 0")
            return
        }
        GLES32.glUniformMatrix2fv(GLES32.glGetUniformLocation(mProgramId, name), 1, false, value, 0)
    }

    fun setMat3(name: String, value: FloatArray) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setMat3] $this mProgramId is 0")
            return
        }
        GLES32.glUniformMatrix3fv(GLES32.glGetUniformLocation(mProgramId, name), 1, false, value, 0)
    }

    fun setMat4(name: String, value: FloatArray) {
        if (mProgramId == 0) {
            LogUtil.e(TAG, "[setMat4] $this mProgramId is 0")
            return
        }
        GLES32.glUniformMatrix4fv(GLES32.glGetUniformLocation(mProgramId, name), 1, false, value, 0)
    }

    private fun createGlProgram(vertexSource: String?, fragmentSource: String?): Int {
        val vertexShader = loadShader(GLES32.GL_VERTEX_SHADER, vertexSource)
        if (vertexShader == 0) {
            LogUtil.e(TAG, "[createGlProgram] $this failed to load vertexShader")
            return 0
        }
        val fragmentShader = loadShader(GLES32.GL_FRAGMENT_SHADER, fragmentSource)
        if (fragmentShader == 0){
            LogUtil.e(TAG, "[createGlProgram] $this failed to load fragmentShader")
            return 0
        }
        var program = GLES32.glCreateProgram()
        if (program == 0) {
            LogUtil.e(TAG, "[createGlProgram] $this failed to create program")
            return 0
        }
        GLES32.glAttachShader(program, vertexShader)
        GLES32.glAttachShader(program, fragmentShader)
        GLES32.glLinkProgram(program)
        val linkStatus = IntArray(1)
        GLES32.glGetProgramiv(program, GLES32.GL_LINK_STATUS, linkStatus, 0)
        if (linkStatus[0] != GLES32.GL_TRUE) {
            LogUtil.e(TAG, "[createGlProgram] $this failed to link program, error message:\n" + GLES32.glGetProgramInfoLog(program))
            GLES32.glDeleteProgram(program)
            program = 0
        }
        GLES32.glDeleteShader(vertexShader)
        GLES32.glDeleteShader(fragmentShader)
        return program
    }

    private fun loadShader(shaderType: Int, source: String?): Int {
        var shader = GLES32.glCreateShader(shaderType)
        if (shader == 0) {
            LogUtil.e(TAG, "[loadShader] failed to create shader")
            return 0
        }
        GLES32.glShaderSource(shader, source)
        GLES32.glCompileShader(shader)
        val compiled = IntArray(1)
        GLES32.glGetShaderiv(shader, GLES32.GL_COMPILE_STATUS, compiled, 0)
        if (compiled[0] == 0) {
            LogUtil.e(TAG, "[loadShader] failed to compile shader, error message:\n" + GLES32.glGetShaderInfoLog(shader))
            GLES32.glDeleteShader(shader)
            shader = 0
        }
        return shader
    }

    fun onDestroy() {
        if (mProgramId != 0) {
            GLES32.glDeleteProgram(mProgramId)
            mProgramId = 0
        }
    }

    override fun toString(): String {
        return "ShaderProgram(" + this.mVertexAssetsPath + ", " + this.mFragmentAssetsPath + ")"
    }
}