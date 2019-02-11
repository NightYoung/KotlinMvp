package com.vince.easyprint.ui.news

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import com.jaeger.library.StatusBarUtil
import com.vince.easyprint.R
import com.vince.easyprint.base.activity.BaseActivity
import com.vince.easyprint.entity.DataClass
import com.vince.library.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_item_detail.*
import java.net.URL

/**
 * <p>作者：黄思程  2017/11/16 11:04
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <>描述：列表Item详情页
 */
class DetailActivity : BaseActivity() {
    /** 确定mItem不会为空，延迟初始化*/
    private lateinit var mItem: DataClass.Movie

    override fun initVariables() {
        mItem = intent.getSerializableExtra(ARG_ITEM) as DataClass.Movie
    }

    override fun initContentView(): Int = R.layout.activity_item_detail

    override fun initViews() {
        StatusBarUtil.setTransparent(mActivity)

        val fragment = NewsDetailFragment.newInstance(mItem.overView)
        supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()

        fab.setOnClickListener {
            ToastUtil.showShort(mItem.title)
        }
    }

    override fun loadData() {
        mItem.let {
            toolbarLayout.title = it.title
            setBackImg(DataClass.convertUrl(it.posterPath))
        }
    }

    private fun setBackImg(url: String) {
        Thread {
            val createFromStream = Drawable.createFromStream(URL(url).openStream(), "123.jpg")
            toolbarLayout.post {
                toolbarLayout.background = createFromStream
            }
        }.start()
    }

    companion object {
        const val ARG_ITEM = "item"

        /*** 跳转当前页面*/
        fun start(context: Activity, movie: DataClass.Movie) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(ARG_ITEM, movie)
            }
            context.startActivity(intent)
        }
    }
}
