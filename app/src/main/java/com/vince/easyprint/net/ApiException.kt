package com.vince.easyprint.net

import com.vince.easyprint.constant.Constants.LOGIN_OVER_DUE

/**
 * <p>作者：黄思程  2018/5/22 17:39
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义错误
 */
class ApiException(val code: Long, msg: String)
    : RuntimeException(msg) {

    constructor(code: Long, msg: String, data: String) : this(code, msg)

    //自定义错误，如登录过期，账号在其他设备登录
    fun loginOverDue(): Boolean {
        //登录过期的失败码
        return code == LOGIN_OVER_DUE
    }

}