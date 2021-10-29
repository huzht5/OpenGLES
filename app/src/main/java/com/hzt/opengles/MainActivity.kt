package com.hzt.opengles

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.hzt.base.activity.BaseActivity
import com.hzt.base.util.showToast
import com.hzt.base.view.ColorPickerView
import com.hzt.opengles.triangle.TriangleActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainTriangleButton: Button = findViewById(R.id.main_triangle_button)
        mainTriangleButton.setOnClickListener{
            TriangleActivity.actionStart(this)
        }

        val mainTestButton: Button = findViewById(R.id.main_test_button)
        mainTestButton.setOnClickListener{
            ColorTestActivity.actionStart(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> "你点击了新增".showToast()
            R.id.remove_item -> showDialog()
        }
        return true
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        val colorPickerView = ColorPickerView(this, null)
        builder.setView(colorPickerView)
        builder.setPositiveButton("好的") { _, _ -> }
        val dialog = builder.create()
        dialog.show()
    }
}