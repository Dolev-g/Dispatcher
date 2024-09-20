package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.dispatcher.domain.repository.article.ArticleRepoFactory
import com.example.dispatcher.domain.repository.article.EnumApiType
import com.example.dispatcher.domain.repository.article.EnumArticleType
import com.example.dispatcher.domain.repository.article.IArticleRepository
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articlesRepository: IArticleRepository = articleRepoFactory.createArticleRepo(EnumArticleType.MOCK)

    private val _searchArticlesLiveData = MutableLiveData<List<ArticleUiModel>>()
    val searchArticlesLiveData: LiveData<List<ArticleUiModel>> get() = _searchArticlesLiveData


    val articlesLiveData = Pager(PagingConfig(pageSize = 20)) {
        articlesRepository.getArticlesPagingSource(EnumApiType.HEADLINES,"")
    }.flow.cachedIn(viewModelScope)

    fun fetchSearchArticles(query: String) {
        Log.i("test", "fetchSearchArticles: ")
        viewModelScope.launch {
            try {
                var topHeadlines = withContext(Dispatchers.IO) {
                    articlesRepository.fetchSearchArticles(query)
                }

                // Print topHeadlines
                Log.d("test", "Fetched articles:")
                topHeadlines.forEach { article ->
                    Log.d("test", "Title: ${article.title}, Source: ${article.source}, Author: ${article.author}")
                }

                _searchArticlesLiveData.postValue(topHeadlines)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching search articles: ${e.message}")
            }
        }
    }

}
