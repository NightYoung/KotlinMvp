package com.vince.easyprint.constant

/**
 * <p>作者：黄思程  2018/5/22 17:22
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：静态常量
 */
object Constants {
    /*** 是否为单元测试*/
    const val isUnitTest = false

    /*** POST方法*/
    const val POST = "POST"
    /*** 未知错误*/
    const val UNKOWN_EXCEPTION = -101L
    /*** 网络错误*/
    const val HTTP_EXCEPTION = -100L
    /*** 请求成功*/
    const val HTTP_200 = 200L
    /*** 500*/
    const val HTTP_500 = 500L
    /*** 404*/
    const val HTTP_404 = 404L
    /*** 502*/
    const val HTTP_502 = 502L
    /*** 503*/
    const val HTTP_503 = 503L

    /*** 登录过期*/
    const val LOGIN_OVER_DUE = 100001L

    /*** 记录应用是否为第一次打开*/
    const val FIRST_OPEN = "first_open"

}