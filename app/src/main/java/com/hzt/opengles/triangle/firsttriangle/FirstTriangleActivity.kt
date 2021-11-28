package com.hzt.opengles.triangle.firsttriangle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hzt.base.activity.BaseActivity
import com.hzt.opengles.R

class FirstTriangleActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, FirstTriangleActivity::class.java)
            context.startActivity(intent)
        }
    }
    private lateinit var mGLSurfaceView: FirstTriangleGLView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_triangle)
        mGLSurfaceView = findViewById(R.id.first_triangle_view)
    }

    override fun onResume() {
        super.onResume()
        mGLSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mGLSurfaceView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mGLSurfaceView.onDestroy()
    }
}