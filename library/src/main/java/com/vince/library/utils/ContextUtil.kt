package com.vince.library.utils

import android.annotation.SuppressLint
import android.content.Context


/**
 * <p>作者：黄思程  2018/5/23 16:16
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：获取全局Context
 */
@SuppressLint("StaticFieldLeak")
object ContextUtil {

    private lateinit var context: Context

    /**
     * @param context 初始化工具类
     */
    fun init(context: Context) {

        ContextUtil.context = context.applicationContext
    }

    /**
     * @return 获取ApplicationContext
     */
    fun withContext(): Context {

        return context
    }
}
