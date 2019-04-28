package com.android.varnit.grabassignment.ui.newsdetail

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class NewsDetailsPresenter : NewsDetailsContract.Presenter {

    private lateinit var view: NewsDetailsContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun attach(view: NewsDetailsContract.View) {
        this.view = view
    }

    override fun loadWebView(url: String) {
        view.loadWebViewFromUrl(url)
    }
}