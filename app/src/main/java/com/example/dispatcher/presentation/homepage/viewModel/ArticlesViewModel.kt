package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dispatcher.domain.repository.article.ArticleRepoFactory
import com.example.dispatcher.data.model.news.TopHeadlines
import com.example.dispatcher.domain.repository.article.EnumArticleType
import com.example.dispatcher.domain.repository.article.IArticleRepository

class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articlesRepository: IArticleRepository = articleRepoFactory.createArticleRepo(EnumArticleType.SERVER)

    val articlesLiveData: LiveData<TopHeadlines?>

    init {
        articlesLiveData = fetchArticles()
    }

    fun fetchArticles(): LiveData<TopHeadlines?> {
        return articlesRepository.fetchArticles()
    }
}

