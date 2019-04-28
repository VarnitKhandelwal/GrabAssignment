package com.android.varnit.grabassignment.di.component

import com.android.varnit.grabassignment.ui.main.MainActivity
import com.android.varnit.grabassignment.di.module.ActivityModule
import dagger.Component

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}