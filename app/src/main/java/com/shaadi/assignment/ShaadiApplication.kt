package com.shaadi.assignment

import android.app.Application
import com.shaadi.assignment.di.component.ApplicationComponent
import com.shaadi.assignment.di.component.DaggerApplicationComponent
import com.shaadi.assignment.di.module.ApplicationModule

class ShaadiApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}