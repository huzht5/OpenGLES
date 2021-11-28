package com.hzt.opengles.triangle.tricolortriangle

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.hzt.base.view.MyGLSurfaceView
import kotlin.math.roundToInt

class TricolorTriangleGLView(context: Context?, attrs: AttributeSet?) : MyGLSurfaceView(context, attrs) {
    private val mRenderer = TricolorTriangleRenderer()

    init {
        setRenderer(mRenderer)
        renderMode = RENDERMODE_WHEN_DIRTY
    }

    fun updateColor(position: String, red: Float, green: Float, blue: Float) {
        when (position) {
            "up" -> {
                mRenderer.mUpColor[0] = red
                mRenderer.mUpColor[1] = green
                mRenderer.mUpColor[2] = blue
            }
            "left" -> {
                mRenderer.mLeftColor[0] = red
                mRenderer.mLeftColor[1] = green
                mRenderer.mLeftColor[2] = blue
            }
            "right" -> {
                mRenderer.mRightColor[0] = red
                mRenderer.mRightColor[1] = green
                mRenderer.mRightColor[2] = blue
            }
            else -> return
        }
        queueEvent { mRenderer.update() }
        requestRender()
    }

    fun getColor(position: String): Int {
        when (position) {
            "up" -> {
                val red = (mRenderer.mUpColor[0] * 255.0F).roundToInt()
                val green = (mRenderer.mUpColor[1] * 255.0F).roundToInt()
                val blue = (mRenderer.mUpColor[2] * 255.0F).roundToInt()
                return Color.rgb(red, green, blue)
            }
            "left" -> {
                val red = (mRenderer.mLeftColor[0] * 255.0F).roundToInt()
                val green = (mRenderer.mLeftColor[1] * 255.0F).roundToInt()
                val blue = (mRenderer.mLeftColor[2] * 255.0F).roundToInt()
                return Color.rgb(red, green, blue)
            }

            "right" -> {
                val red = (mRenderer.mRightColor[0] * 255.0F).roundToInt()
                val green = (mRenderer.mRightColor[1] * 255.0F).roundToInt()
                val blue = (mRenderer.mRightColor[2] * 255.0F).roundToInt()
                return Color.rgb(red, green, blue)
            }
            else -> return 0
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        queueEvent { mRenderer.onDestroy() }
    }
}