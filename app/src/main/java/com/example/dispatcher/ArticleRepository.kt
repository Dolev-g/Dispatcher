package com.example.dispatcher.repository

import android.content.Context
import com.example.dispatcher.R
import com.example.dispatcher.model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class ArticleRepository(context: Context) {

    private val articles = mutableListOf<Article>()

    init {
        // Load mock articles from the JSON file
        val inputStream = context.resources.openRawResource(R.raw.mock_articles)
        val reader = InputStreamReader(inputStream)
        val articleListType = object : TypeToken<List<Article>>() {}.type
        val mockArticles: List<Article> = Gson().fromJson(reader, articleListType)
        articles.addAll(mockArticles)
    }

    fun addArticle(article: Article) {
        articles.add(article)
    }

    fun getArticles(): List<Article> {
        return articles.toList()
    }
}
