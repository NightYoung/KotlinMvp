package com.vince.library.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

/**
 * <p>作者：黄思程  2018/5/23 16:17
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：单例Toast
 */
object ToastUtil {
    private const val TIME_SHORT = Toast.LENGTH_SHORT
    private const val TIME_LONG = Toast.LENGTH_LONG

    private var mToast: Toast? = null

    /**
     * 显示short message
     *
     * @param resId string string资源id
     */
    fun showShort(resId: Int) {
        showToast(ContextUtil.withContext(), resId, TIME_SHORT)
    }

    /**
     * 显示short message
     *
     * @param message 显示msg
     */
    fun showShort(message: String) {
        showToast(ContextUtil.withContext(), message, TIME_SHORT)
    }

    /**
     * 显示long message
     *
     * @param resId string string资源id
     */
    fun showLong(resId: Int) {
        showToast(ContextUtil.withContext(), resId, TIME_LONG)
    }

    /**
     * 显示long message
     *
     * @param message 显示msg
     */
    fun showLong(message: String) {
        showToast(ContextUtil.withContext(), message, TIME_LONG)
    }

    @SuppressLint("ShowToast")
    private fun showToast(mContext: Context, text: String, duration: Int) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, duration)
        } else {
            mToast?.setText(text)
        }
        mToast?.show()
    }

    @SuppressLint("ShowToast")
    private fun showToast(mContext: Context, resId: Int, duration: Int) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, resId, duration)
        } else {
            mToast?.setText(resId)
        }
        mToast?.show()
    }
}