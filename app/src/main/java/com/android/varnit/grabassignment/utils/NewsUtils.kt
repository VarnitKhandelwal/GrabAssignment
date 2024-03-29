package com.android.varnit.grabassignment.utils

import android.content.Context
import android.net.ConnectivityManager

object NewsUtils {
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        return cm!!.activeNetworkInfo != null
    }
}