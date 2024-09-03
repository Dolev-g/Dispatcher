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

    init {
        articlesLiveData.value = articleRepository.getArticles()
    }

    fun addTask(article: Article) {
        articleRepository.addArticle(article)
        articlesLiveData.value = articleRepository.getArticles()
    }

    fun getTasksLiveData(): LiveData<List<Article>> {
        return articlesLiveData
    }
}
