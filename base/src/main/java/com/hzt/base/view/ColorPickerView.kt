package com.hzt.base.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.hzt.base.R
import com.hzt.base.application.MyApplication
import com.hzt.base.util.showToast
import com.hzt.base.view.ColorPickerPicture.ColorPickerCallback

class ColorPickerView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(MyApplication.context).inflate(R.layout.color_picker, this)
        val colorPickerPicture: ColorPickerPicture = findViewById(R.id.color_picker_picture)

        val hueEditText: EditText = findViewById(R.id.edit_text_hue)
        hueEditText.setText(colorPickerPicture.getHue().toString())
        hueEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                try {
                    val hue = hueEditText.text.toString().toInt()
                    if (hue < 0 || hue > 360) {
                        "非法输入！请输入0~360！".showToast()
                    } else {
                        colorPickerPicture.setHue(hue.toFloat())
                        hueEditText.clearFocus()
                        (MyApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                            .hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                } catch (e: NumberFormatException) {
                    "非法输入！请输入0~360！".showToast()
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val saturationEditText: EditText = findViewById(R.id.edit_text_saturation)
        saturationEditText.setText(colorPickerPicture.getSaturation().toString())
        saturationEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                try {
                    val saturation = saturationEditText.text.toString().toInt()
                    if (saturation < 0 || saturation > 100) {
                        "非法输入！请输入0~100！".showToast()
                    } else {
                        colorPickerPicture.setSaturation(saturation.toFloat() / 100.0F)
                        saturationEditText.clearFocus()
                        (MyApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                            .hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                } catch (e: NumberFormatException) {
                    "非法输入！请输入0~100！".showToast()
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val valueEditText: EditText = findViewById(R.id.edit_text_value)
        valueEditText.setText(colorPickerPicture.getValue().toString())
        valueEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                try {
                    val value = valueEditText.text.toString().toInt()
                    if (value < 0 || value > 100) {
                        "非法输入！请输入0~100！".showToast()
                    } else {
                        colorPickerPicture.setValue(value.toFloat() / 100.0F)
                        valueEditText.clearFocus()
                        (MyApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                            .hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                } catch (e: NumberFormatException) {
                    "非法输入！请输入0~100！".showToast()
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val redEditText: EditText = findViewById(R.id.edit_text_red)
        redEditText.setText(colorPickerPicture.getRed().toString())
        redEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                try {
                    val red = redEditText.text.toString().toInt()
                    if (red < 0 || red > 255) {
                        "非法输入！请输入0~255！".showToast()
                    } else {
                        colorPickerPicture.setRed(red)
                        redEditText.clearFocus()
                        (MyApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                            .hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                } catch (e: NumberFormatException) {
                    "非法输入！请输入0~255！".showToast()
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val greenEditText: EditText = findViewById(R.id.edit_text_green)
        greenEditText.setText(colorPickerPicture.getGreen().toString())
        greenEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                try {
                    val green = greenEditText.text.toString().toInt()
                    if (green < 0 || green > 255) {
                        "非法输入！请输入0~255！".showToast()
                    } else {
                        colorPickerPicture.setGreen(green)
                        greenEditText.clearFocus()
                        (MyApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                            .hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                } catch (e: NumberFormatException) {
                    "非法输入！请输入0~255！".showToast()
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val blueEditText: EditText = findViewById(R.id.edit_text_blue)
        blueEditText.setText(colorPickerPicture.getBlue().toString())
        blueEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                try {
                    val blue = blueEditText.text.toString().toInt()
                    if (blue < 0 || blue > 255) {
                        "非法输入！请输入0~255！".showToast()
                    } else {
                        colorPickerPicture.setBlue(blue)
                        blueEditText.clearFocus()
                        (MyApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                            .hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                } catch (e: NumberFormatException) {
                    "非法输入！请输入0~255！".showToast()
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val colorEditText: EditText = findViewById(R.id.edit_text_color)
        colorEditText.setText(colorPickerPicture.getColorStr().replace("#", ""))
        colorEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                try {
                    val text = "#" + colorEditText.text.toString()
                    val color = Color.parseColor(text)
                    colorPickerPicture.setColorStr(text)
                    colorEditText.clearFocus()
                    (MyApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                        .hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                } catch (e: IllegalArgumentException) {
                    "非法输入！请输入正确的颜色值！".showToast()
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val imageView: ImageView = findViewById(R.id.image_view_color)
        imageView.setBackgroundColor(colorPickerPicture.getColor())

        colorPickerPicture.setColorPickerCallback(object : ColorPickerCallback {
            override fun onColorChange() {
                hueEditText.setText(colorPickerPicture.getHue().toString())
                saturationEditText.setText(colorPickerPicture.getSaturation().toString())
                valueEditText.setText(colorPickerPicture.getValue().toString())
                redEditText.setText(colorPickerPicture.getRed().toString())
                greenEditText.setText(colorPickerPicture.getGreen().toString())
                blueEditText.setText(colorPickerPicture.getBlue().toString())
                colorEditText.setText(colorPickerPicture.getColorStr().replace("#", ""))
                imageView.setBackgroundColor(colorPickerPicture.getColor())
            }
        })
    }
}
