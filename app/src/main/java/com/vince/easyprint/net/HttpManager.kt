package com.vince.easyprint.net

import com.vince.easyprint.BuildConfig
import com.vince.easyprint.constant.Constants
import com.vince.library.utils.JsonUtil
import com.vince.library.utils.LogUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * <p>作者：黄思程  2018/5/22 17:58
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述： Retrofit网络请求单例实现
 */
object HttpManager {
    /*** 超时时间*/
    private const val connectTimeOut = 15L
    private const val readTimeOut = 20L
    private const val writeTimeOut = 20L

    /*** 静态属性和方法定义*/
    lateinit var mRetrofit: Retrofit
    lateinit var mHttpClient: OkHttpClient

    /*** new一个单例*/
    fun newInstance(): Retrofit {
        mHttpClient = getHttpClient()

        mRetrofit = Retrofit.Builder()
            .baseUrl(ApiStore.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(mHttpClient)
            .build()

        return mRetrofit
    }

    /*** 获取HttpClient*/
    private fun getHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val interceptor = ParamsInterceptor.Builder()
            .addHeaderParamsMap(addHeader())
            .addParamsMap(addBody())
            .build()
        builder.addInterceptor(interceptor)
        builder.addNetworkInterceptor(interceptor)

        // Log信息拦截器
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor)
        }

        //设置超时和重连
        builder.connectTimeout(connectTimeOut, TimeUnit.SECONDS)
        builder.readTimeout(readTimeOut, TimeUnit.SECONDS)
        builder.writeTimeout(writeTimeOut, TimeUnit.SECONDS)

        //错误重连
        builder.retryOnConnectionFailure(true)
        return if (BuildConfig.DEBUG) {
            val logInterceptor = HttpLoggingInterceptor(HttpLogger())
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(logInterceptor)
                .build()
        } else {
            builder.build()
        }
    }

    /*** 在该方法中添加统一的头信息*/
    fun addHeader(): MutableMap<String, String> {
        val property: MutableMap<String, String> = mutableMapOf()

        if (Constants.isUnitTest) {
            /*property.put()*/
        } else {
            /*property.put()*/
        }

        return property
    }

    /*** 在该方法中添加统一的body信息*/
    fun addBody(): MutableMap<String, String> {
        val property: MutableMap<String, String> = mutableMapOf()

        if (Constants.isUnitTest) {
            /*property.put()*/
        } else {
            /*property.put()*/
        }

        return property
    }
}


private class HttpLogger : HttpLoggingInterceptor.Logger {
    private val mMessage = StringBuilder()

    override fun log(msg: String) {
        var message = msg
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0)
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if (message.startsWith("{") && message.endsWith("}")
            || message.startsWith("[") && message.endsWith("]")
        ) {
            message = JsonUtil.formatJson(message)
        }
        mMessage.append(message + "\n")
        // 请求或者响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            LogUtil.d(mMessage.toString())
        }
    }
}