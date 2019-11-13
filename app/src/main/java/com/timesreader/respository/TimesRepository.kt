package com.timesreader.respository

import com.timesreader.model.Error
import com.timesreader.model.Success
import com.timesreader.model.ViewState
import com.timesreader.network.TimesApiService

class TimesRepository(private val timesApiService: TimesApiService) : Repository {
    override suspend fun getTopArticles(): ViewState {
        return try {
            val response = timesApiService.getTopArticles()
            Success(response)
        } catch (e: Exception) {
            Error(e)
        }
    }
}