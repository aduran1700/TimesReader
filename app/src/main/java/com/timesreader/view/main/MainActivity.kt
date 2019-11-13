package com.timesreader.view.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.timesreader.R
import com.timesreader.model.*
import com.timesreader.util.gone
import com.timesreader.util.visible
import com.timesreader.view.article.ArticleActivity
import com.timesreader.viewmodel.ArticleListViewModel
import com.timesreader.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), ArticleSelectListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ArticleListViewModel>
    lateinit var viewModel: ArticleListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val adapter =  ArticleRecyclerViewAdapter(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ArticleListViewModel::class.java]


        recycler_view?.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        viewModel.getViewModelLiveData().observe(this, Observer {
            when (it) {
                Loading -> {
                    error_message.gone()
                    recycler_view.gone()
                    progress_bar.visible()
                }
                is Success<Any> ->  {
                    error_message.gone()
                    recycler_view.visible()
                    progress_bar.gone()
                    adapter.loadArticles((it.data as TimesData).results)
                }
                is Error -> {
                    error_message.visible()
                    recycler_view.gone()
                    progress_bar.gone()
                }
            }
        })
        viewModel.getTopArticles()
    }

    override fun onItemSelection(article: Article) {
        val intent = Intent(this, ArticleActivity::class.java)
        val bundle = Bundle()
        bundle.putString(ArticleActivity.URL, article.url)
        bundle.putString(ArticleActivity.SECTION, article.section)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}
