package com.vince.easyprint.base.fragment

import android.os.Bundle
import android.view.View
import com.vince.easyprint.base.BasePresenter

/**
 * <p>作者：黄思程  2018/5/22 18:47
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：带网络请求的Fragment
 */
abstract class MvpFragment<P : BasePresenter<*>> : BaseFragment() {
    lateinit var mPresenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mPresenter = createPresenter()

        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * @return 创建Presenter
     * */
    protected abstract fun createPresenter(): P

    override fun onDestroyView() {
        super.onDestroyView()

        mPresenter.detachView()
    }
}