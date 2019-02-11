package com.vince.library.utils

/**
 * <p>作者：Night  2019/1/29 14:25
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：全局工具类
 */
object Globle {

    /** 根据资源id获取String*/
    fun getString(resId: Int): String = ContextUtil.withContext().getString(resId)


}