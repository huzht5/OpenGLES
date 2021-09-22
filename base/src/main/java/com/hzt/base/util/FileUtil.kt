package com.hzt.base.util

import com.hzt.base.application.MyApplication

object FileUtil {

    fun getTextFromAssets(filePath: String): String {
        val inputStream = MyApplication.context.resources.assets.open(filePath)
        val length = inputStream.available()
        val buffer = ByteArray(length)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer)
    }
}