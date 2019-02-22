package com.vince.easyprint.net

import android.os.Looper
import android.text.TextUtils
import com.vince.easyprint.constant.Constants.HTTP_200
import com.vince.easyprint.constant.Constants.HTTP_404
import com.vince.easyprint.constant.Constants.HTTP_500
import com.vince.easyprint.constant.Constants.HTTP_502
import com.vince.easyprint.constant.Constants.HTTP_503
import com.vince.easyprint.constant.Constants.HTTP_EXCEPTION
import com.vince.easyprint.constant.Constants.UNKOWN_EXCEPTION
import com.vince.library.utils.LogUtil
import com.vince.library.utils.ToastUtil
import retrofit2.HttpException
import rx.Subscriber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

/**
 * <p>作者：黄思程  2018/5/22 17:20
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：网络请求回掉接口封装
 */
abstract class ApiCallback<M> : Subscriber<M>() {
    /*** 自定义忽略错误(在这里添加   定义一个类，定义各种错误码，然后将不需要处理的错误码添加在这个集合里)*/
    private val mIgnore = arrayOf(HTTP_200, HTTP_500, HTTP_404, HTTP_502)

    /*** 请求成功
     ** @param model 消费类型 */
    abstract fun onSuccess(model: M)

    /*** 请求失败
     * @param code 状态码
     * @param msg  信息  */
    abstract fun onFailure(code: Long, msg: String = "")

    /*** 无论是成功还是失败都会Finish*/
    abstract fun onFinish()

    override fun onNext(t: M) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        val msg = e.message ?: ""

        when (e) {
            //网络错误
            is HttpException -> {
                val code = e.code().toLong()

                if (code == HTTP_404 || code == HTTP_500 || code == HTTP_503) {
                    //无法连接到服务器的集中处理
                    toastException(code, msg)
                    cannotConnectToService(code, msg)
                }
                onFailure(code, msg)
            }

            //自定义错误
            is ApiException -> {
                val code = e.code

                if (e.loginOverDue()) {
                    toastException(code, msg)
                    LogUtil.d("登录过期,请重新登录")
                }
            }

            //连接超时，无法解析地址
            is UnknownHostException, is SocketTimeoutException -> {
                cannotConnectToService(HTTP_EXCEPTION, msg)
                onFailure(HTTP_EXCEPTION, msg)
            }

            else -> {
                toastException(0, e.message ?: msg)
                onFailure(UNKOWN_EXCEPTION, msg)
            }
        }

        onFinish()
    }

    override fun onCompleted() {
        onFinish()
    }

    /*** 无法连接到服务器的处理*/
    private fun cannotConnectToService(code: Long, msg: String) {

        //doSomething
        toastException(code, msg)
    }

    private fun toastException(code: Long, msg: String) {
        if (Looper.myLooper() == null || needToast(code)) {
            return
        }

        if (!TextUtils.isEmpty(msg)) {
            ToastUtil.showShort(msg)
        }
    }

    private fun needToast(code: Long): Boolean {

        return Arrays.asList(*mIgnore).contains(code)
    }

}