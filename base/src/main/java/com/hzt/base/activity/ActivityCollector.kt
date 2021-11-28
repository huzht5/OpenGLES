package com.hzt.base.activity

import android.app.Activity
import com.hzt.base.util.LogUtil

object ActivityCollector {
    private const val TAG = "ActivityCollector"
    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAllActivities() {
        LogUtil.i(TAG, "[finishAllActivities]")
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
        android.os.Process.killProcess(android.os.Process.myPid())
    }
}