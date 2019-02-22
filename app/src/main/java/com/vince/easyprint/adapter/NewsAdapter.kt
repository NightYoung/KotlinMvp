package com.vince.easyprint.adapter

import android.app.Activity
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.vince.easyprint.R
import com.vince.easyprint.entity.DataClass
import com.vince.easyprint.loadImage
import com.vince.easyprint.ui.news.DetailActivity

/**
 * <p>作者：黄思程  2018/5/23 14:25
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：新闻列表页数据适配器
 */
class NewsAdapter(data: MutableList<DataClass.Movie>) :
        BaseQuickAdapter<DataClass.Movie, BaseViewHolder>(R.layout.news_item, data) {

    override fun convert(holder: BaseViewHolder, item: DataClass.Movie) {
        holder.setText(R.id.newsContent, item.overView)

        val imgView = holder.getView<ImageView>(R.id.newsImage)
        imgView.loadImage(mContext, DataClass.convertUrl(item.posterPath))

        holder.getView<View>(R.id.rl_news).setOnClickListener {
            val movie = mData[holder.layoutPosition]
            DetailActivity.start(mContext as Activity, movie)
        }
    }
}