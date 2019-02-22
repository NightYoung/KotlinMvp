package com.vince.easyprint.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * <p>作者：黄思程  2018/5/23 10:39
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：ViewPager适配器
 */
class PagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private var dataList = mutableListOf<Fragment>()

    constructor(manager: FragmentManager, dataList: MutableList<Fragment>) : this(manager) {
        this.dataList = dataList
    }

    override fun getItem(position: Int): Fragment = dataList[position]

    override fun getCount(): Int = dataList.size
}