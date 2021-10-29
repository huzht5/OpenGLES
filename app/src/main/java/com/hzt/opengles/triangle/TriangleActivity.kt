package com.hzt.opengles.triangle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hzt.base.activity.BaseActivity
import com.hzt.base.common.Scene
import com.hzt.base.common.ShaderProgram
import com.hzt.base.util.FileUtil
import com.hzt.base.view.MyGLSurfaceView
import com.hzt.opengles.R

class TriangleActivity : BaseActivity() {
    lateinit var mGLSurfaceView: MyGLSurfaceView

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, TriangleActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_triangle)
        mGLSurfaceView = findViewById(R.id.triangle_view)
        //mGLSurfaceView.prepare(TriangleRenderer())
        val shaderProgram = ShaderProgram(FileUtil.getTextFromAssets("shader/triangle/vertex.glsl"),
            FileUtil.getTextFromAssets("shader/triangle/fragment.glsl"))
        //mGLSurfaceView.prepare(Scene())
    }

    override fun onResume() {
        super.onResume()
        mGLSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mGLSurfaceView.onPause()
    }
}