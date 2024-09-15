package com.example.dispatcher.domain.repository.article

import androidx.lifecycle.LiveData
import com.example.dispatcher.data.model.news.TopHeadlines

interface IArticleRepository {
    fun fetchArticles(): LiveData<TopHeadlines?>
    fun fetchSearchArticles(q:String): LiveData<TopHeadlines?>
}
