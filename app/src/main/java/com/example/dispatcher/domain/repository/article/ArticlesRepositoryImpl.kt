package com.example.dispatcher.domain.repository.article

import android.content.Context
import android.util.Log
import com.example.dispatcher.data.api.Secrets
import com.example.dispatcher.data.api.news.NewsApi
import com.example.dispatcher.data.api.news.NewsServiceApi
import com.example.dispatcher.data.model.news.TopHeadlines
import com.example.dispatcher.data.network.NetworkManager

class ArticlesRepositoryImpl(context: Context) : IArticleRepository {

    private val newsServiceApi = NetworkManager.createService(NewsServiceApi::class.java)

    override suspend fun fetchArticles(): TopHeadlines? {
        return try {
            val topHeadlines = newsServiceApi.getTopHeadlines(NewsApi.COUNTRY_CODE, Secrets.API_KEY)
            topHeadlines
        } catch (e: Exception) {
            Log.e("TAG", "Error fetching top headlines: ${e.message}")
            null
        }
    }

    override suspend fun fetchSearchArticles(q: String): TopHeadlines? {
        return try {
            val searchHeadlines = newsServiceApi.getSearchArticles(q, Secrets.API_KEY)
            searchHeadlines
        } catch (e: Exception) {
            Log.e("TAG", "Error fetching search articles: ${e.message}")
            null
        }
    }
}
