package com.example.dispatcher.domain.repository.article

import android.util.Log
import androidx.paging.PagingSource
import com.example.dispatcher.data.api.Secrets
import com.example.dispatcher.data.api.news.NewsApi
import com.example.dispatcher.data.api.news.NewsServiceApi
import com.example.dispatcher.data.network.NetworkManager
import com.example.dispatcher.domain.repository.article.ArticleMapper.mapToUiModelList
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

class ArticlesRepositoryImpl() : IArticleRepository {

    private val newsServiceApi = NetworkManager.createService(NewsServiceApi::class.java)

    override fun getArticlesPagingSource(apiType: EnumApiType): PagingSource<Int, ArticleUiModel> {
        return ArticlesPagingSource(this, apiType)
    }

    override suspend fun fetchArticles(): List<ArticleUiModel> {
        return try {
            val tempTopHeadlines = newsServiceApi.getTopHeadlines(NewsApi.COUNTRY_CODE, Secrets.API_KEY)
            val topHeadlines = mapToUiModelList(tempTopHeadlines.articles)
            topHeadlines
        } catch (e: Exception) {
            Log.e("TAG", "Error fetching top headlines: ${e.message}")
            emptyList()
        }
    }

    override suspend fun fetchSearchArticles(q: String): List<ArticleUiModel> {
        return try {
            val tempSearchHeadlines = newsServiceApi.getSearchArticles(q, Secrets.API_KEY)
            val searchHeadlines = mapToUiModelList(tempSearchHeadlines.articles)
            searchHeadlines
        } catch (e: Exception) {
            Log.e("TAG", "Error fetching search articles: ${e.message}")
            emptyList()
        }
    }

    override suspend fun fetchArticlesPaged(page: Int, pageSize:Int): List<ArticleUiModel> {
        val tempTopHeadlines = newsServiceApi.getTopHeadlinesPaged(NewsApi.COUNTRY_CODE, Secrets.API_KEY, page, pageSize)
        return mapToUiModelList(tempTopHeadlines.articles)
    }

    override suspend fun fetchSearchArticlesPaged(page: Int, pageSize:Int): List<ArticleUiModel> {
        val tempTopHeadlines = newsServiceApi.getSearchHeadlinesPaged(NewsApi.COUNTRY_CODE, Secrets.API_KEY, page, pageSize)
        return mapToUiModelList(tempTopHeadlines.articles)
    }
}
