package com.vince.easyprint.constant

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * <p>作者：黄思程  2018/5/23 16:31
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：页面跳转
 */
object PageRoute {
    /**
     * 跳转activity方式一
     *
     * @param context 上下文
     * @param intent  跳转意图
     */
    fun to(context: Context?, intent: Intent?) {
        if (null != context && null != intent) {
            context.startActivity(intent)
        }
    }

    /**
     * 跳转activity方式二
     *
     * @param context       上下文
     * @param activityClass Activity类名
     */
    fun to(context: Context?, activityClass: Class<out Activity>?) {
        if (null != context && null != activityClass) {
            val e = Intent(context, activityClass)
            context.startActivity(e)
        }
    }

}