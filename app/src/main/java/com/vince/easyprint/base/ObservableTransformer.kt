package com.vince.easyprint.base

import com.vince.easyprint.constant.Constants
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * <p>作者：黄思程  2018/5/23 10:12
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：ObservableTransformer
 */
class ObservableTransformer<T> : Observable.Transformer<T, T> {
    override fun call(observable: Observable<T>): Observable<T> {
        return if (Constants.isUnitTest) {
            observable
                    .subscribeOn(Schedulers.immediate())
                    .observeOn(Schedulers.immediate())
        } else {
            observable
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}