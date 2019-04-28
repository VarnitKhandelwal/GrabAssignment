package com.android.varnit.grabassignment.ui.main

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class MainPresenter: MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
        view.showNewsFragment() // as default
    }
}