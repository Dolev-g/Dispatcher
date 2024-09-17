package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.domain.repository.article.ArticleRepoFactory
import com.example.dispatcher.domain.repository.article.EnumArticleType
import com.example.dispatcher.domain.repository.article.IArticleRepository
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articlesRepository: IArticleRepository = articleRepoFactory.createArticleRepo(EnumArticleType.MOCK)

    private val _articlesLiveData = MutableLiveData<List<ArticleUiModel>>()
    val articlesLiveData: LiveData<List<ArticleUiModel>> get() = _articlesLiveData

    private val _searchArticlesLiveData = MutableLiveData<List<ArticleUiModel>>()
    val searchArticlesLiveData: LiveData<List<ArticleUiModel>> get() = _searchArticlesLiveData

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        articlesRepository.fetchArticles().observeForever { topHeadlines ->
            val articleNewList = mutableListOf<ArticleUiModel>()

            topHeadlines?.articles?.forEach { article ->
                val newArticle = ArticleUiModel(
                    title = article.title,
                    description = article.description,
                    urlToImage = article.urlToImage,
                    author = article.author,
                    publishedAt = article.publishedAt
                )
                articleNewList.add(newArticle)
            }

            _articlesLiveData.postValue(articleNewList)
        }
    }

    fun fetchSearchArticles(q:String) {
        articlesRepository.fetchSearchArticles(q).observeForever { topHeadlines ->
            val articleNewList = mutableListOf<ArticleUiModel>()

            topHeadlines?.articles?.forEach { article ->
                val newArticle = ArticleUiModel(
                    title = article.title,
                    description = article.description,
                    urlToImage = article.urlToImage,
                    author = article.author,
                    publishedAt = article.publishedAt
                )
                articleNewList.add(newArticle)
            }

            _searchArticlesLiveData.postValue(articleNewList)
        }
    }


}


