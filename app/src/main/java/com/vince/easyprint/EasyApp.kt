package com.vince.easyprint

import android.app.Application
import com.vince.library.utils.ContextUtil
import com.vince.library.utils.LogUtil

/**
 * <p>作者：黄思程  2018/5/22 18:49
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：BaseApp
 */
class EasyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        ContextUtil.init(this)
        LogUtil.init(true)
    }
}