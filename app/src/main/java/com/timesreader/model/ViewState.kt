package com.timesreader.model

sealed class ViewState
object Loading: ViewState()
data class Success<out T : Any>(val data: T) : ViewState()
data class Error(val exception: Throwable, val message: String = exception.localizedMessage ?: "") : ViewState()