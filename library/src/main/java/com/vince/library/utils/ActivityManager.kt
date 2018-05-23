@file:Suppress("unused")

package com.vince.library.utils

import android.app.Activity
import java.util.*

/**
 * <p>作者：黄思程  2018/5/23 16:16
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：Activity工具类
 */
object ActivityManager {
    private val mActivities = Collections
            .synchronizedList(LinkedList<Activity>())

    /***
     * @param activity 将指定Activity入栈
     */
    fun pushActivity(activity: Activity) {
        if (mActivities == null) {
            return
        }

        mActivities.add(activity)
    }

    /***
     * @param activity 将制定Activity出栈
     */
    fun popActivity(activity: Activity) {
        if (mActivities == null || mActivities.isEmpty()) {
            return
        }

        mActivities.remove(activity)
    }

    /***
     * @return 获取栈顶Activity
     */
    fun currentActivity(): Activity? {
        return if (mActivities == null || mActivities.isEmpty()) {
            null
        } else mActivities[mActivities.size - 1]

    }

    /***
     * @param activity  结束指定Activity
     */
    fun finishActivity(activity: Activity?) {
        if (mActivities == null || mActivities.isEmpty()) {
            return
        }

        if (activity != null) {
            mActivities.remove(activity)
            activity.finish()
        }
    }

    /*** 结束当前页面(栈顶Activity) */
    fun finishCurrentActivity() {
        if (mActivities == null || mActivities.isEmpty()) {
            return
        }

        val activity = mActivities[mActivities.size - 1]
        finishActivity(activity)
    }

    /**
     * @param activity 指定的类名
     * @return 指定Activity
     */
    fun findActivity(activity: Class<*>): Activity? {
        var target: Activity? = null
        if (mActivities != null) {
            for (mActivity in mActivities) {
                if (mActivity.javaClass == activity) {
                    target = mActivity
                    break
                }
            }
        }

        return target
    }

    /*** 结束所有Activity */
    fun finishAllActivity() {
        if (mActivities == null) {
            return
        }
        for (activity in mActivities) {
            activity.finish()
        }
        mActivities.clear()
    }
}