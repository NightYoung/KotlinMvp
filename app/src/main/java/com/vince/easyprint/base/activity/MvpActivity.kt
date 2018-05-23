package com.vince.easyprint.base.activity

import android.os.Bundle
import com.vince.easyprint.base.BasePresenter

/**
 * <p>作者：黄思程  2018/5/22 18:47
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：带网络请求的Activity
 */
abstract class MvpActivity<P : BasePresenter<*>> : BaseActivity() {
    protected lateinit var mvpPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        mvpPresenter = createPresenter()

        super.onCreate(savedInstanceState)
    }

    /**
     * @return 创建Presenter
     * */
    protected abstract fun createPresenter(): P

    override fun onDestroy() {
        super.onDestroy()

        mvpPresenter.detachView()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        mvpPresenter.unsubscribe()
    }


}