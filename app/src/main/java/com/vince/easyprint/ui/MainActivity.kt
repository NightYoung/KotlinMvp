package com.vince.easyprint.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.vince.easyprint.R
import com.vince.easyprint.adapter.PagerAdapter
import com.vince.easyprint.base.activity.BaseActivity
import com.vince.easyprint.entity.TabEntity
import com.vince.easyprint.ui.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * <p>作者：黄思程  2018/5/23 11:14
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：新闻列表页
 */
class MainActivity : BaseActivity(), OnTabSelectListener,
    ViewPager.OnPageChangeListener {
    /** fragment集合*/
    private var dataList: MutableList<Fragment> = mutableListOf()
    /** table集合*/
    private var mTabEntities: ArrayList<CustomTabEntity> = ArrayList()

    /** 选择的icon*/
    private val mSelectIcon = listOf(
        R.drawable.news_select,
        R.drawable.constellation_select,
        R.drawable.joke_select
    )
    /** 未选中的icon*/
    private val mUnSelectIcon = listOf(
        R.drawable.news,
        R.drawable.constellation,
        R.drawable.joke
    )
    /** table的标题*/
    private lateinit var mTitles: List<String>

    override fun initContentView(): Int = R.layout.activity_main

    override fun initVariables() {
        mTitles = listOf(
            getString(R.string.news),
            getString(R.string.movie),
            getString(R.string.joke)
        )

        mTitles.forEachIndexed { index, _ ->
            mTabEntities.add(TabEntity(mTitles[index], mSelectIcon[index], mUnSelectIcon[index]))
        }

        dataList.add(NewsFragment.newInstance(mTitles[0]))
        dataList.add(NewsFragment.newInstance(mTitles[1]))
        dataList.add(NewsFragment.newInstance(mTitles[2]))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        swipeBackLayout.setEnableGesture(false)
    }

    override fun initViews() {
        vpList.adapter = PagerAdapter(supportFragmentManager, dataList)
        ctlTable.setTabData(mTabEntities)
        ctlTable.currentTab = 0

        ctlTable.setOnTabSelectListener(this)
        vpList.addOnPageChangeListener(this)
    }

    override fun loadData() {

    }

    override fun onTabSelect(position: Int) {

        vpList.currentItem = position
    }

    override fun onTabReselect(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

        ctlTable.currentTab = position
    }
}