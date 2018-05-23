package com.vince.easyprint.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * <p>作者：黄思程  2018/5/22 18:47
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：基类Fragment
 */
abstract class BaseFragment : Fragment() {
    protected lateinit var mActivity: Activity

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mActivity = context as Activity
        initVariables()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(initContentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
    }

    /*** 初始化变量,包括Intent数据 */
    protected abstract fun initVariables()

    /***
     * @return 设置layout
     */
    protected abstract fun initContentView(): Int

    /*** 初始化View和绑定事件
     * @param v fragment的View
     */
    protected abstract fun initView(v: View)

    /*** 加载数据 */
    protected abstract fun loadData()
}