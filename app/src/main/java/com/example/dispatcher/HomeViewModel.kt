package com.example.dispatcher.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.model.Article
import com.example.dispatcher.repository.ArticleRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepository = ArticleRepository(application)
    private val articlesLiveData = MutableLiveData<List<Article>>()

    private val FirstTwoWordsLiveData = MutableLiveData<List<String>>()

    init {
        articlesLiveData.value = articleRepository.getArticles()
        updateFirstTwoWordsArticles()
    }

    fun addFirstTwoWords(article: Article) {
        articleRepository.addArticle(article)
        articlesLiveData.value = articleRepository.getArticles()
        updateFirstTwoWordsArticles()
    }

    fun getFirstTwoWordsLiveData(): LiveData<List<String>> {
        return FirstTwoWordsLiveData
    }

    private fun updateFirstTwoWordsArticles() {
        val articles = articlesLiveData.value
        val truncatedArticles = articles?.map { article ->
            article.content.split(" ").take(2).joinToString(" ")
        } ?: emptyList()
        FirstTwoWordsLiveData.value = truncatedArticles
    }
}
