package com.android.varnit.grabassignment.di.module

import com.android.varnit.grabassignment.api.ApiServiceInterface
import com.android.varnit.grabassignment.ui.news.NewsContract
import com.android.varnit.grabassignment.ui.news.NewsPresenter
import com.android.varnit.grabassignment.ui.newsdetail.NewsDetailsContract
import com.android.varnit.grabassignment.ui.newsdetail.NewsDetailsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
@Module
class FragmentModule {

    @Provides
    fun provideNewsPresenter(): NewsContract.Presenter {
        return NewsPresenter()
    }

    @Provides
    fun provideNewsDetailsPresenter(): NewsDetailsContract.Presenter {
        return NewsDetailsPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}