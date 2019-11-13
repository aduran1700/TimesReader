package com.timesreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timesreader.model.*
import com.timesreader.respository.Repository
import com.timesreader.util.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(
    private val repository: Repository,
    private val contextProvider: CoroutineContextProvider) : ViewModel() {
    private val viewModelMutableLiveData: MutableLiveData<ViewState> by lazy {
        MutableLiveData<ViewState>()
    }

    fun getViewModelLiveData() : LiveData<ViewState> = viewModelMutableLiveData


    fun getTopArticles() {
        viewModelMutableLiveData.value = Loading
        viewModelScope.launch(contextProvider.IO) {
            val viewState = repository.getTopArticles()
            withContext(contextProvider.Main) {
                viewModelMutableLiveData.value = viewState
            }
        }
    }
}