package com.timesreader.root.module

import com.timesreader.view.main.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    internal abstract fun provideMainActivity(activity: MainActivity): MainActivity
}