package com.hzt.base.util

import com.hzt.base.application.MyApplication

object FileUtil {

    fun getTextFromAssets(filePath: String): String {
        val inputStream = MyApplication.context.resources.assets.open(filePath)
        inputStream.buffered().reader().use { reader ->
            return reader.readText()
        }
    }
}