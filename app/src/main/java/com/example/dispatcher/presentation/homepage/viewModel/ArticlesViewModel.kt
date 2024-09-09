package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.domain.repository.article.ArticleRepoFactory
import com.example.dispatcher.presentation.homepage.model.Article
import com.example.dispatcher.domain.repository.article.EnumArticleType
import com.example.dispatcher.domain.repository.article.IArticleRepository

class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articlesRepository: IArticleRepository = articleRepoFactory.createArticleRepo(EnumArticleType.SERVER)

    private val _articlesLiveData = MutableLiveData<MutableList<Article>>()
    val articlesLiveData: LiveData<MutableList<Article>> = _articlesLiveData

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        val articlesList = articlesRepository.fetchArticles()
        _articlesLiveData.value = articlesList
    }
}

