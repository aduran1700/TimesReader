package com.timesreader.respository

import com.timesreader.model.ViewState


interface Repository {
    suspend fun getTopArticles() : ViewState
}