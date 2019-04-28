package com.android.varnit.grabassignment.di.component

import com.android.varnit.grabassignment.application.GrabApplication
import com.android.varnit.grabassignment.di.module.ApplicationModule
import dagger.Component

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: GrabApplication)

}