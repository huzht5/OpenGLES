package com.hzt.opengles

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.hzt.base.activity.BaseActivity
import com.hzt.base.util.showToast
import com.hzt.opengles.triangle.firsttriangle.FirstTriangleActivity
import com.hzt.opengles.triangle.singlecolortriangle.SingleColorTriangleActivity
import com.hzt.opengles.triangle.tricolortriangle.TricolorTriangleActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstTriangleButton: Button = findViewById(R.id.first_triangle_button)
        firstTriangleButton.setOnClickListener{
            FirstTriangleActivity.actionStart(this)
        }

        val singleColorTriangleButton: Button = findViewById(R.id.single_color_triangle_button)
        singleColorTriangleButton.setOnClickListener{
            SingleColorTriangleActivity.actionStart(this)
        }

        val tricolorTriangleButton: Button = findViewById(R.id.tricolor_triangle_button)
        tricolorTriangleButton.setOnClickListener{
            TricolorTriangleActivity.actionStart(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> "你点击了新增".showToast()
            R.id.remove_item -> "你点击了移除".showToast()
        }
        return true
    }
}