package com.example.dispatcher.domain.homepage.repo

import com.example.dispatcher.presentation.homepage.model.Article

interface InterfaceArticleRepo {
    fun getArticles(): MutableList<Article>
}
