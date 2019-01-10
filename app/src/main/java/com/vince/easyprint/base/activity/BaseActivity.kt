package com.vince.easyprint.base.activity

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.vince.easyprint.base.SwipeBackActivity
import com.vince.library.utils.ActivityManager

/**
 * <p>作者：黄思程  2018/5/22 18:47
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：1.隐藏ActionBar
 *                    2.设置为竖屏
 */
abstract class BaseActivity : SwipeBackActivity() {
    protected lateinit var mActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.pushActivity(this)
        mActivity = this

        initVariables()
        hideStatusBar()
        val layoutId = initContentView()
        if (layoutId != 0) {
            setContentView(layoutId)
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        initViews()
        loadData()
    }

    /** 初始化变量,包括Intent数据*/
    protected abstract fun initVariables()

    /** @return 设置layout资源*/
    protected abstract fun initContentView(): Int

    /** 初始化View和绑定事件*/
    protected abstract fun initViews()

    /** 加载数据*/
    protected abstract fun loadData()

    override fun onDestroy() {
        ActivityManager.popActivity(this)
        super.onDestroy()
    }

    /*** 隐藏虚拟按键，并且全屏 */
    protected fun hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT in 16..18) { // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or View.SYSTEM_UI_FLAG_IMMERSIVE)
            decorView.systemUiVisibility = uiOptions
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    /*** 隐藏系统状态栏*/
    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            val localLayoutParams = window.attributes
            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        }
    }
}