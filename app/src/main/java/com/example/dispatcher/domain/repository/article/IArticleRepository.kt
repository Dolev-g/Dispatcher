package com.example.dispatcher.domain.repository.article

import com.example.dispatcher.presentation.homepage.model.ArticleUiModel
import androidx.paging.PagingSource

interface IArticleRepository {
    fun getArticlesPagingSource(apiType: EnumApiType): PagingSource<Int, ArticleUiModel>
    suspend fun fetchArticles(): List<ArticleUiModel>
    suspend fun fetchSearchArticles(query: String): List<ArticleUiModel>
    suspend fun fetchArticlesPaged(page: Int, pageSize:Int): List<ArticleUiModel>
    suspend fun fetchSearchArticlesPaged(page: Int, pageSize:Int): List<ArticleUiModel>
}

