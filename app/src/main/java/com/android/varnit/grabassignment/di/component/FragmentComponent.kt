package com.android.varnit.grabassignment.di.component

import com.android.varnit.grabassignment.di.module.FragmentModule
import com.android.varnit.grabassignment.ui.news.NewsFragment
import com.android.varnit.grabassignment.ui.newsdetail.NewsDetailsFragment
import dagger.Component

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(newsFragment: NewsFragment)
    fun inject(newsDetailFragment: NewsDetailsFragment)
}