package com.vince.easyprint.base

import com.vince.easyprint.net.ApiStore
import com.vince.easyprint.net.HttpManager
import rx.Observable
import rx.Subscriber
import rx.subscriptions.CompositeSubscription

/**
 * <p>作者：黄思程  2018/5/22 18:27
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：Presenter基类
 */
open class BasePresenter<V>(v: V) {
    var mvpView: V? = null
    lateinit var apiStores: ApiStore
    private val mCompositeSubscription by lazy { CompositeSubscription() }

    init {
        attachView(v)
    }

    /**
     * 绑定view，并创建apiStores
     * @param mvpView view*/
    private fun attachView(mvpView: V) {
        this.mvpView = mvpView
        apiStores = HttpManager.newInstance().create(ApiStore::class.java)
    }

    /*** 释放view，并解绑RxJava*/
    fun detachView() {
        unsubscribe()
        this.mvpView = null
    }

    /*** RxJava取消注册，以避免内存泄露*/
    fun unsubscribe() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe()
        }
    }

    /**
     * RxJava添加注册，使用CompositeSubscription绑定被观察者和观察者，方便管理
     * @param observable 被观察者，会使用到装饰者
     * @param subscriber 观察者   */
    protected fun <T> addSubscription(observable: Observable<T>, subscriber: Subscriber<T>) {
        val transformer: Observable.Transformer<T, T> = ObservableTransformer()
        val subscription = observable.compose(transformer).subscribe(subscriber)
        mCompositeSubscription.add(subscription)
    }
}

