package com.android.varnit.grabassignment.ui.news

import com.android.varnit.grabassignment.api.ApiServiceInterface
import com.android.varnit.grabassignment.models.NewsModel
import com.android.varnit.grabassignment.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class NewsPresenter: NewsContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: NewsContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: NewsContract.View) {
        this.view = view
    }

    override fun loadData() {
        var subscribe = api.getNewsList("Bitcoin", Constants.NEWS_API_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ newsData: NewsModel ->
                    view.showProgress(false)
                    view.loadDataSuccess(newsData)
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }
}