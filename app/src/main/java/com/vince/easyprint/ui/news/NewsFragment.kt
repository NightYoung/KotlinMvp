package com.vince.easyprint.ui.news

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import com.vince.easyprint.R
import com.vince.easyprint.adapter.NewsAdapter
import com.vince.easyprint.base.fragment.MvpLazyFragment
import com.vince.easyprint.entity.DataClass
import com.vince.library.utils.LogUtil
import com.vince.library.utils.ToastUtil
import kotlinx.android.synthetic.main.layout_fragment_news_list.*

/**
 * <p>作者：黄思程  2018/5/23 10:08
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：新闻列表页
 */
class NewsFragment : MvpLazyFragment<NewsPresenter>(),
    OnRefreshLoadmoreListener, NewsContract.View {
    private var page = 1
    private var sortBy = "popularity.desc"

    private var mDataList: MutableList<DataClass.Movie> = mutableListOf()
    private var mAdapter: NewsAdapter = NewsAdapter(mDataList)
    private var mContent = ""
    private var isLoadMore = false

    override fun createPresenter(): NewsPresenter = NewsPresenter(this)

    override fun initContentView(): Int = R.layout.layout_fragment_news_list

    override fun initVariables() {
        mContent = arguments?.getString(CONTENT) ?: mContent
    }

    override fun initViews(v: View) {
        rvNews.layoutManager = LinearLayoutManager(mActivity)
        rvNews.adapter = mAdapter
        mAdapter.isFirstOnly(true)
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)

        srlNewsList.autoRefresh()
        srlNewsList.setOnRefreshLoadmoreListener(this)
    }

    override fun showLoading() {}

    override fun hideLoading() {
        srlNewsList.finishRefresh(true)
        srlNewsList.finishLoadmore(true)
    }

    override fun loadData() {

        mvpPresenter.requestNewsData(sortBy, page)
    }

    override fun onGetNewsDataSuccess(source: DataClass.DataSource) {
        if (!isLoadMore) {
            mDataList.clear()
        }

        mDataList.addAll(source.movies)
        mAdapter.notifyDataSetChanged()
    }

    override fun onGetNewsDataFailed(code: Long, msg: String?) {
        LogUtil.d(code)
        msg?.let { ToastUtil.showShort(it) }
    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        page += 1
        isLoadMore = true
        loadData()
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
        page = 1
        isLoadMore = false
        loadData()
    }

    companion object {
        const val CONTENT = "content"

        /*** 单例*/
        fun newInstance(content: String): NewsFragment {
            val args = Bundle()
            args.putString(CONTENT, content)
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}