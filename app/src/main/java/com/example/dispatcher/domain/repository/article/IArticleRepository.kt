package com.example.dispatcher.domain.repository.article

import androidx.lifecycle.LiveData
import com.example.dispatcher.data.model.news.TopHeadlines
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

interface IArticleRepository {
    fun fetchArticles(): LiveData<List<ArticleUiModel>>
    fun fetchSearchArticles(q:String): LiveData<List<ArticleUiModel>>
}
