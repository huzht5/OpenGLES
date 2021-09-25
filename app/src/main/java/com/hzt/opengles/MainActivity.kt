package com.hzt.opengles

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.hzt.base.activity.BaseActivity
import com.hzt.opengles.triangle.TriangleActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainTriangleButton: Button = findViewById(R.id.main_triangle_button)
        mainTriangleButton.setOnClickListener{
            TriangleActivity.actionStart(this);
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "你点击了新增", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "你点击了移除", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}