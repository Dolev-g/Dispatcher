package com.example.dispatcher.domain.repository.article

import android.content.Context
import com.example.dispatcher.R
import com.example.dispatcher.data.model.news.TopHeadlines
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class MockArticleRepository(context: Context) : IArticleRepository {

    private val mockTopHeadlines: TopHeadlines

    init {
        val inputStream = context.resources.openRawResource(R.raw.mock_articles)
        val reader = InputStreamReader(inputStream)
        val topHeadlinesType = object : TypeToken<TopHeadlines>() {}.type
        mockTopHeadlines = Gson().fromJson(reader, topHeadlinesType)
    }

    override suspend fun fetchArticles(): TopHeadlines? {
        return mockTopHeadlines
    }

    override suspend fun fetchSearchArticles(q: String): TopHeadlines? {
        val filteredArticles = mockTopHeadlines.articles.filter { article ->
            article.title.contains(q, ignoreCase = true) || article.description.contains(q, ignoreCase = true)
        }

        return TopHeadlines(
            status = mockTopHeadlines.status,
            totalResults = filteredArticles.size,
            articles = ArrayList(filteredArticles)
        )
    }
}
