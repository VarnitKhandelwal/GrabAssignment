package com.android.varnit.grabassignment.ui.newsdetail

import com.android.varnit.grabassignment.ui.base.BaseContract

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class NewsDetailsContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun loadWebViewFromUrl(url: String)
        // fun loadMessageError() // When it's a real request, this function should be implemented, too
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadWebView(url: String) // Let's assume that this will be a retrofit request
    }
}