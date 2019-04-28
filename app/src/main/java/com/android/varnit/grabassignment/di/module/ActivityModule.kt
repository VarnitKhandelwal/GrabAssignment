package com.android.varnit.grabassignment.di.module

import android.app.Activity
import com.android.varnit.grabassignment.ui.main.MainContract
import com.android.varnit.grabassignment.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}