package com.android.varnit.grabassignment.di.module

import android.app.Application
import com.android.varnit.grabassignment.application.GrabApplication
import com.android.varnit.grabassignment.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
@Module
class ApplicationModule(private val baseApp: GrabApplication) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}