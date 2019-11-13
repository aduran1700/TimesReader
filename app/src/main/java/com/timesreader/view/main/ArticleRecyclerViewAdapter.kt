package com.timesreader.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.timesreader.R
import com.timesreader.databinding.ArticleRowItemBinding
import com.timesreader.model.Article

class ArticleRecyclerViewAdapter(private val listener: ArticleSelectListener) : RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {

    private val articles = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ArticleRowItemBinding>(
            inflater, R.layout.article_row_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    fun loadArticles(articles: List<Article>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ArticleRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.root.setOnClickListener{listener.onItemSelection(article)}
            binding.executePendingBindings()
        }
    }
}