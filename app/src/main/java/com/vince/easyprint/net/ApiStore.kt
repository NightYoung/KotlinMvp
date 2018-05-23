package com.vince.easyprint.net

import com.vince.easyprint.entity.DataClass
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * <p>作者：黄思程  2018/5/23 10:19
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：网络请求仓库
 */
interface ApiStore {
    companion object {
        /*** 基地址*/
        const val baseUrl: String = "http://api.themoviedb.org"
    }


    /*** 获取新闻数据*/
    @POST("/3/discover/movie")
    @FormUrlEncoded
    fun requestNewsData(@Field("sort_by") sortBy: String,
                        @Field("api_key") apiKey: String,
                        @Field("page") page: Int): Observable<DataClass.DataSource>
}