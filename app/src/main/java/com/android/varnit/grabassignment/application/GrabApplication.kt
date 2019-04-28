package com.android.varnit.grabassignment.application

import android.app.Application
import com.android.varnit.grabassignment.di.component.ApplicationComponent
import com.android.varnit.grabassignment.di.component.DaggerApplicationComponent
import com.android.varnit.grabassignment.di.module.ApplicationModule

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class GrabApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: GrabApplication private set
    }
}