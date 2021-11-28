package com.hzt.opengles.triangle.singlecolortriangle

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.hzt.base.view.MyGLSurfaceView
import kotlin.math.roundToInt

class SingleColorTriangleGLView(context: Context?, attrs: AttributeSet?) : MyGLSurfaceView(context, attrs) {
    private val mRenderer = SingleColorTriangleRenderer()

    init {
        setRenderer(mRenderer)
        renderMode = RENDERMODE_WHEN_DIRTY
    }

    fun updateColor(red: Float, green: Float, blue: Float) {
        mRenderer.mColor[0] = red
        mRenderer.mColor[1] = green
        mRenderer.mColor[2] = blue
        requestRender()
    }

    fun getColor(): Int {
        val red = (mRenderer.mColor[0] * 255.0F).roundToInt()
        val green = (mRenderer.mColor[1] * 255.0F).roundToInt()
        val blue = (mRenderer.mColor[2] * 255.0F).roundToInt()
        return Color.rgb(red, green, blue)
    }

    override fun onDestroy() {
        super.onDestroy()
        queueEvent { mRenderer.onDestroy() }
    }
}