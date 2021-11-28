package com.hzt.opengles.triangle.tricolortriangle

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.hzt.base.activity.BaseActivity
import com.hzt.base.view.ColorPickerView
import com.hzt.opengles.R

class TricolorTriangleActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, TricolorTriangleActivity::class.java)
            context.startActivity(intent)
        }
    }
    private lateinit var mGLSurfaceView: TricolorTriangleGLView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tricolor_triangle)
        mGLSurfaceView = findViewById(R.id.tricolor_triangle_view)

        val changeUpColorButton: Button = findViewById(R.id.change_up_color_button)
        changeUpColorButton.setOnClickListener{
            selectColor("up")
        }

        val changeLeftColorButton: Button = findViewById(R.id.change_left_color_button)
        changeLeftColorButton.setOnClickListener{
            selectColor("left")
        }

        val changeRightColorButton: Button = findViewById(R.id.change_right_color_button)
        changeRightColorButton.setOnClickListener{
            selectColor("right")
        }
    }

    private fun selectColor(position: String) {
        val builder = AlertDialog.Builder(this)
        val colorPickerView = ColorPickerView(this, null)
        colorPickerView.mColorPickerPicture.setColor(mGLSurfaceView.getColor(position))
        builder.setView(colorPickerView)
        builder.setPositiveButton("确定") { _, _ ->
            mGLSurfaceView.updateColor(position,
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