package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dispatcher.domain.repository.article.ArticleRepoFactory
import com.example.dispatcher.domain.repository.article.EnumArticleType
import com.example.dispatcher.domain.repository.article.IArticleRepository
import com.example.dispatcher.presentation.homepage.model.ArticleView
import kotlinx.coroutines.launch

class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articlesRepository: IArticleRepository = articleRepoFactory.createArticleRepo(EnumArticleType.MOCK)

    private val _articlesLiveData = MutableLiveData<List<ArticleView>>()
    val articlesLiveData: LiveData<List<ArticleView>> get() = _articlesLiveData

    private val _searchArticlesLiveData = MutableLiveData<List<ArticleView>>()
    val searchArticlesLiveData: LiveData<List<ArticleView>> get() = _searchArticlesLiveData

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            val topHeadlines = articlesRepository.fetchArticles()

            val articleNewList = topHeadlines?.articles?.map { article ->
                ArticleView(
                    title = article.title,
                    description = article.description ?: "No description available",
                    urlToImage = article.urlToImage ?: "default_image_url",
                    author = article.author ?: "unknown author",
                    publishedAt = article.publishedAt ?: "unknown date"
                )
            } ?: emptyList()

            _articlesLiveData.postValue(articleNewList)
        }
    }

    fun fetchSearchArticles(q: String) {
        viewModelScope.launch {
            val topHeadlines = articlesRepository.fetchSearchArticles(q)

            val articleNewList = topHeadlines?.articles?.map { article ->
                ArticleView(
                    title = article.title,
                    description = article.description ?: "No description available",
                    urlToImage = article.urlToImage ?: "default_image_url",
                    author = article.author ?: "unknown author",
                    publishedAt = article.publishedAt ?: "unknown date"
                )
            } ?: emptyList()

            _searchArticlesLiveData.postValue(articleNewList)
        }
    }
}
