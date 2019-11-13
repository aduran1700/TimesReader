package com.timesreader.view.main

import com.timesreader.model.Article

interface ArticleSelectListener {
    fun onItemSelection(article: Article)
}