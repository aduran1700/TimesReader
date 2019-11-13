package com.timesreader.view.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.timesreader.R
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.content_article.*

class ArticleActivity : AppCompatActivity() {
    companion object {
        const val URL = "ARTICLE_URL"
        const val SECTION = "ARTICLE_SECTION"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = intent.extras?.getString(SECTION)
        web_view.loadUrl(intent.extras?.getString(URL))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
