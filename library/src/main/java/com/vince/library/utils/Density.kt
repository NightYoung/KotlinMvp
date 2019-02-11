package com.vince.library.utils

/**
 * <p>作者：Night  2019/1/29 13:46
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：尺寸转换工具类
 */


/**
 * dp尺寸转换为px(像素)尺寸
 * @param dp dp尺寸
 *
 * dp为开发者使用的尺寸单位
 * px为系统使用的尺寸(像素点)
 */
fun dp2px(dp: Float): Int {

    val scale = ContextUtil.withContext().resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}


/**
 * 将px尺寸转换为dp尺寸
 * @param px px尺寸
 */
fun px2dp(px: Float): Int {

    val scale = ContextUtil.withContext().resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}