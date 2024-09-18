package com.example.dispatcher.domain.repository.article

import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

interface IArticleRepository {
    suspend fun fetchArticles(): List<ArticleUiModel>
    suspend fun fetchSearchArticles(q:String): List<ArticleUiModel>
}
