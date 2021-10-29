package com.hzt.opengles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hzt.base.activity.BaseActivity

class ColorTestActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, ColorTestActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_test)
    }
}