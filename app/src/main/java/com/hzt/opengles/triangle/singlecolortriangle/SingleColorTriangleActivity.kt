package com.hzt.opengles.triangle.singlecolortriangle

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.hzt.base.activity.BaseActivity
import com.hzt.base.view.ColorPickerView
import com.hzt.opengles.R

class SingleColorTriangleActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, SingleColorTriangleActivity::class.java)
            context.startActivity(intent)
        }
    }
    private lateinit var mGLSurfaceView: SingleColorTriangleGLView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_color_triangle)
        mGLSurfaceView = findViewById(R.id.single_color_triangle_view)

        val changeColorButton: Button = findViewById(R.id.change_color_button)
        changeColorButton.setOnClickListener{
            selectColor()
        }
    }

    private fun selectColor() {
        val builder = AlertDialog.Builder(this)
        val colorPickerView = ColorPickerView(this, null)
        colorPickerView.mColorPickerPicture.setColor(mGLSurfaceView.getColor())
        builder.setView(colorPickerView)
        builder.setPositiveButton("确定") { _, _ ->
            mGLSurfaceView.updateColor(
                colorPickerView.mColorPickerPicture.getRed().toFloat() / 255.0F,
                colorPickerView.mColorPickerPicture.getGreen().toFloat() / 255.0F,
                colorPickerView.mColorPickerPicture.getBlue().toFloat() / 255.0F)
        }
        val dialog = builder.create()
        dialog.show()
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