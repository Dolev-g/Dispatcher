package com.example.dispatcher.domain.repository.article

import androidx.lifecycle.LiveData
import com.example.dispatcher.data.model.news.TopHeadlines

interface IArticleRepository {
    suspend fun fetchArticles(): TopHeadlines?
    suspend fun fetchSearchArticles(q:String): TopHeadlines?
}
