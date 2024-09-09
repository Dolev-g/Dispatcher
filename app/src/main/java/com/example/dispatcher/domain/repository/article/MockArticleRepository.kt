package com.example.dispatcher.domain.repository.article

import android.content.Context
import com.example.dispatcher.R
import com.example.dispatcher.presentation.homepage.model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class MockArticleRepository(context: Context) : IArticleRepository {

    private val articles = mutableListOf<Article>()

    init {
        val inputStream = context.resources.openRawResource(R.raw.mock_articles)
        val reader = InputStreamReader(inputStream)
        val articleListType = object : TypeToken<List<Article>>() {}.type
        val mockArticles: List<Article> = Gson().fromJson(reader, articleListType)
        articles.addAll(mockArticles)
    }

    override fun fetchArticles(): MutableList<Article> {
        return articles
    }
}
