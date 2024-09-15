package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.domain.repository.article.ArticleRepoFactory
import com.example.dispatcher.domain.repository.article.EnumArticleType
import com.example.dispatcher.domain.repository.article.IArticleRepository
import com.example.dispatcher.presentation.homepage.model.ArticleView

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
        articlesRepository.fetchArticles().observeForever { topHeadlines ->
            val articleNewList = mutableListOf<ArticleView>()

            topHeadlines?.articles?.forEach { article ->
                val newArticle = ArticleView(
                    title = article.title,
                    description = article.description ?: "No description available",
                    urlToImage = article.urlToImage ?: "default_image_url",
                    author = article.author ?: "unknown author",
                    publishedAt = article.publishedAt ?: "unknown date"
                )
                articleNewList.add(newArticle)
            }

            _articlesLiveData.postValue(articleNewList)
        }
    }

    fun fetchSearchArticles(q:String) {
        articlesRepository.fetchSearchArticles(q).observeForever { topHeadlines ->
            val articleNewList = mutableListOf<ArticleView>()

            topHeadlines?.articles?.forEach { article ->
                val newArticle = ArticleView(
                    title = article.title,
                    description = article.description ?: "No description available",
                    urlToImage = article.urlToImage ?: "default_image_url",
                    author = article.author ?: "unknown author",
                    publishedAt = article.publishedAt ?: "unknown date"
                )
                articleNewList.add(newArticle)
            }

            _searchArticlesLiveData.postValue(articleNewList)
        }
    }


}


