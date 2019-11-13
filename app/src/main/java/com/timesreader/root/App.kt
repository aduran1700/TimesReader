package com.timesreader.root

import com.timesreader.root.component.ApplicationComponent
import com.timesreader.root.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val component : ApplicationComponent = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)

        return component
    }
}