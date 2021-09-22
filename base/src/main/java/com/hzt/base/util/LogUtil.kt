package com.hzt.base.util

import android.util.Log

object LogUtil {
    private const val TAG = "hzt0123 ";
    private const val VERBOSE = 1;
    private const val DEBUG = 2;
    private const val INFO = 3;
    private const val WARN = 4;
    private const val ERROR = 5;
    private var level = VERBOSE

    fun v(tag: String, msg: String) {
        if (level <= VERBOSE) {
            Log.v(TAG + tag, msg);
        }
    }

    fun d(tag: String, msg: String) {
        if (level <= DEBUG) {
            Log.d(TAG + tag, msg);
        }
    }

    fun i(tag: String, msg: String) {
        if (level <= INFO) {
            Log.i(TAG + tag, msg);
        }
    }

    fun w(tag: String, msg: String) {
        if (level <= WARN) {
            Log.w(TAG + tag, msg);
        }
    }

    fun e(tag: String, msg: String) {
        if (level <= ERROR) {
            Log.e(TAG + tag, msg);
        }
    }
}