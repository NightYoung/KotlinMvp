package com.vince.library.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * <p>作者：黄思程  2018/5/23 16:17
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述： Logger的二次封装
 */
object LogUtil {
    private const val TAG = "<<<Night"

    fun init(openLog: Boolean) {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .tag(TAG)
                .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return openLog
            }
        })
    }

    fun d(msg: String, vararg args: Any) {
        Logger.d(msg, *args)
    }

    fun d(tag: String, msg: String, vararg args: Any) {
        Logger.t(tag).d(msg, *args)
    }

    fun d(args: Any) {
        Logger.d(args)
    }

    fun d(tag: String, args: Any) {
        Logger.t(tag).d(args)
    }

    fun e(msg: String, vararg args: Any) {
        Logger.e(msg, *args)
    }

    fun e(tag: String, msg: String, vararg args: Any) {
        Logger.t(tag).e(msg, *args)
    }

    fun e(throwable: Throwable, message: String, vararg args: Any) {
        Logger.e(throwable, message, *args)
    }

    fun e(tag: String, throwable: Throwable, message: String, vararg args: Any) {
        Logger.t(tag).e(throwable, message, *args)
    }

    fun i(message: String, vararg args: Any) {
        Logger.i(message, *args)
    }

    fun i(tag: String, message: String, vararg args: Any) {
        Logger.t(tag).i(message, *args)
    }

    fun v(message: String, vararg args: Any) {
        Logger.v(message, *args)
    }

    fun v(tag: String, message: String, vararg args: Any) {
        Logger.t(tag).v(message, *args)
    }

    fun w(tag: String, message: String, vararg args: Any) {
        Logger.t(tag).w(message, *args)
    }

    fun w(message: String, vararg args: Any) {
        Logger.w(message, *args)
    }

    fun wtf(message: String, vararg args: Any) {
        Logger.wtf(message, *args)
    }

    fun wtf(tag: String, message: String, vararg args: Any) {
        Logger.t(tag).wtf(message, *args)
    }

    fun json(json: String) {
        Logger.json(json)
    }

    fun json(tag: String, json: String) {
        Logger.t(tag).json(json)
    }

    fun xml(xml: String) {
        Logger.xml(xml)
    }

    fun xml(tag: String, xml: String) {
        Logger.t(tag).xml(xml)
    }
}