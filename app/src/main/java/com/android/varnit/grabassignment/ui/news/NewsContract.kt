package com.android.varnit.grabassignment.ui.news

import com.android.varnit.grabassignment.models.NewsModel
import com.android.varnit.grabassignment.ui.base.BaseContract

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class NewsContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(model: NewsModel)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
    }
}