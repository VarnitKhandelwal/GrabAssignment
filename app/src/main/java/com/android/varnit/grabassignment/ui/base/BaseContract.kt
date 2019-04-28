package com.android.varnit.grabassignment.ui.base

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class BaseContract {

    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View
}