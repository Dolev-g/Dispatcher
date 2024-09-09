package com.example.dispatcher.domain.repository.article

import com.example.dispatcher.presentation.homepage.model.Article

interface IArticleRepository {
    fun fetchArticles(): MutableList<Article>
}
