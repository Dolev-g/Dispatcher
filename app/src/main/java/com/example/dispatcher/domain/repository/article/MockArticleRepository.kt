package com.example.dispatcher.domain.repository.article

import android.content.Context
import androidx.paging.PagingSource
import com.example.dispatcher.R
import com.example.dispatcher.data.model.news.TopHeadlines
import com.example.dispatcher.domain.repository.article.ArticleMapper.mapToUiModelList
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel
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

    override fun getArticlesPagingSource(apiType: EnumApiType, query: String): PagingSource<Int, ArticleUiModel> {
        return MockArticlesPagingSource(this, apiType, query)
    }

    override suspend fun fetchArticlesPaged(page: Int, pageSize: Int): List<ArticleUiModel> {
        val start = (page - 1) * pageSize
        val end = minOf(start + pageSize, mockTopHeadlines.articles.size)
        val pagedArticles = mockTopHeadlines.articles.subList(start, end)

        return mapToUiModelList(pagedArticles)
    }

    override suspend fun fetchSearchArticles(query: String): List<ArticleUiModel> {
        val filteredArticles = mockTopHeadlines.articles.filter { article ->
            article.title.contains(query, ignoreCase = true) || article.description.contains(query, ignoreCase = true)
        }

        return mapToUiModelList(filteredArticles)
    }
}
