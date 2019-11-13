package com.timesreader.root.module

import com.timesreader.network.TimesApiService
import com.timesreader.respository.Repository
import com.timesreader.respository.TimesRepository

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRepository(service: TimesApiService) : Repository {
        return TimesRepository(service)
    }
}