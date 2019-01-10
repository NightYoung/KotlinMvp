package com.vince.easyprint.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.vince.easyprint.R
import com.vince.easyprint.entity.DataClass

/**
 * <p>作者：Night  2019/1/8 22:42
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：KotlinMvp
 */
class MusicAdapter(data: MutableList<DataClass.Movie>) :
    BaseQuickAdapter<DataClass.Movie, BaseViewHolder>(R.layout.news_item, data) {


    override fun convert(helper: BaseViewHolder?, item: DataClass.Movie?) {



    }

}