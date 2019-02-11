package com.vince.library.utils

import android.os.Handler
import android.view.View

/**
 * <p>作者：Night  2019/1/29 14:07
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：Handler扩展工具类
 */

inline fun Handler.postDelay(delayMillis: Long, crossinline action: () -> Unit): Runnable {

    val runnable = Runnable { action() }
    postDelayed(runnable, delayMillis)
    return runnable
}

inline fun View.postDelay(delayMillis: Long, crossinline action: () -> Unit): Runnable {

    val runnable = Runnable { action() }
    postDelayed(runnable, delayMillis)
    return runnable
}