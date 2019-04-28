package com.android.varnit.grabassignment.ui.main

import com.android.varnit.grabassignment.ui.base.BaseContract

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class MainContract {

    interface View: BaseContract.View {
        fun showNewsDetailsFragment(newsUrl: String)
        fun showNewsFragment()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View>
}