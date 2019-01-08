package com.vince.easyprint.ui.flash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.vince.easyprint.R
import com.vince.easyprint.base.activity.BaseActivity
import com.vince.easyprint.constant.Constants.FIRST_OPEN
import com.vince.easyprint.constant.PageRoute
import com.vince.easyprint.ui.login.LoginActivity
import com.vince.library.utils.SpUtil
import kotlinx.android.synthetic.main.activity_flash.*

/**
 * <p>作者：黄思程  2018/5/23 15:05
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：启动页
 */
class FlashActivity : BaseActivity() {

    override fun initVariables() {

    }

    override fun initContentView(): Int {
        hideBottomUIMenu()
        return R.layout.activity_flash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swipeBackLayout.setEnableGesture(false)
    }

    override fun initViews() {

    }

    override fun loadData() {
        val isFirstOpen = SpUtil.getInstance(mActivity).getBoolean(FIRST_OPEN, true)

        if (isFirstOpen) {
            SpUtil.getInstance(mActivity).put(FIRST_OPEN, false)
            PageRoute.to(mActivity, LoginActivity::class.java)
        } else {
            flFlash.animate()
                .alpha(1f)
                .setDuration(500)
                .withEndAction {
                    PageRoute.to(mActivity, LoginActivity::class.java)
                    finish()
                }
        }
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, FlashActivity::class.java)
            starter.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(starter)
        }
    }
}
