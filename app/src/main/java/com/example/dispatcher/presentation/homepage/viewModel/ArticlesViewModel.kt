package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dispatcher.domain.repository.article.ArticleRepoFactory
import com.example.dispatcher.domain.repository.article.EnumArticleType
import com.example.dispatcher.domain.repository.article.IArticleRepository
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articlesRepository: IArticleRepository = articleRepoFactory.createArticleRepo(EnumArticleType.SERVER)

    private val _articlesLiveData = MutableLiveData<List<ArticleUiModel>>()
    val articlesLiveData: LiveData<List<ArticleUiModel>> get() = _articlesLiveData

    private val _searchArticlesLiveData = MutableLiveData<List<ArticleUiModel>>()
    val searchArticlesLiveData: LiveData<List<ArticleUiModel>> get() = _searchArticlesLiveData

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            try {
                val topHeadlines = withContext(Dispatchers.IO) {
                    articlesRepository.fetchArticles()
                }
                _articlesLiveData.postValue(topHeadlines)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching articles: ${e.message}")
            }
        }
    }

    fun fetchSearchArticles(query: String) {
        viewModelScope.launch {
            try {
                val topHeadlines = withContext(Dispatchers.IO) {
                    articlesRepository.fetchSearchArticles(query)
                }
                _searchArticlesLiveData.postValue(topHeadlines)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching search articles: ${e.message}")
            }
        }
    }

}
