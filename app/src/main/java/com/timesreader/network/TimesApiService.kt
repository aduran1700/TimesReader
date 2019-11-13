package com.timesreader.network

import com.timesreader.model.TimesData
import retrofit2.http.GET

interface TimesApiService {

    @GET("/svc/topstories/v2/home.json")
    suspend fun getTopArticles() : TimesData
}