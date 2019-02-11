package com.vince.easyprint.base.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.Utils
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper

/**
 * <p>作者：黄思程  2018/5/23 10:15
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：侧滑返回
 */
@SuppressLint("Registered")
abstract class SwipeBackActivity : AppCompatActivity(), SwipeBackActivityBase {
    private lateinit var mHelper: SwipeBackActivityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.mHelper = SwipeBackActivityHelper(this)
        this.mHelper.onActivityCreate()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        this.mHelper.onPostCreate()
    }

    override fun scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this)
        this.swipeBackLayout.scrollToFinishActivity()
    }

    override fun setSwipeBackEnable(enable: Boolean) {
        this.swipeBackLayout.setEnableGesture(enable)
    }

    override fun getSwipeBackLayout(): SwipeBackLayout {

        return this.mHelper.swipeBackLayout
    }

}