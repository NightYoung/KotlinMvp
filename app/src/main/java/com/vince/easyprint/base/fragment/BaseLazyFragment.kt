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
 * <p>描述：懒加载Fragment基类
 */
abstract class BaseLazyFragment : Fragment() {
    /*** View是否被创建*/
    private var isViewCreated = false
    /*** 是否加载过数据*/
    private var isDataLoad = false
    protected lateinit var mActivity: Activity

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mActivity = context as Activity
        initVariables()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(initContentView(), container, false)

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        lazyLoad()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        initViews(view)
    }

    override fun onStart() {
        super.onStart()
        lazyLoad()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isViewCreated = false
    }

    /*** 设置数据是否加载,可以在数据加载失败时设置为false*/
    protected fun setDataLoad(isLoad: Boolean) {

        this.isDataLoad = isLoad
    }

    /*** 获取数据是否加载*/
    protected fun isDataLoad(): Boolean = isDataLoad

    /*** 懒加载数据*/
    private fun lazyLoad() {
        if (isViewCreated && userVisibleHint && !isDataLoad) {
            loadData()
            isDataLoad = true
        }
    }

    protected abstract fun initContentView(): Int

    /*** 初始化变量,包括Intent数据*/
    protected abstract fun initVariables()

    /*** 初始化View和绑定事件*/
    protected abstract fun initViews(view: View)

    /*** 加载数据*/
    protected abstract fun loadData()

}