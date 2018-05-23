package com.vince.easyprint.net

/**
 * <p>作者：黄思程  2018/5/22 17:39
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义错误
 */
class ApiException : RuntimeException {
    private val errorCode: Long
    private val errorMsg: String
    private val data: String

    /**
     * @param errorCode errorCode
     * @param errorMsg  errorMsg
     */
    constructor(errorCode: Long, errorMsg: String) : super(errorMsg) {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
        this.data = ""
    }

    /**
     * @param errorCode errorCode
     * @param errorMsg  errorMsg
     * @param data      data
     */
    constructor(errorCode: Long, errorMsg: String, data: String) : super(errorMsg) {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
        this.data = data
    }

    fun code(): Long {

        return errorCode
    }

    fun msg(): String {

        return errorMsg
    }

    fun data(): String {

        return data
    }

    //自定义错误，如登录过期，账号在其他设备登录
    fun loginOverDue(): Boolean {
        //登录过期的失败码
        return errorCode == 100001L
    }
}