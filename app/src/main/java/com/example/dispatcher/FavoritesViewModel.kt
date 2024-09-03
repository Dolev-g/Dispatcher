package com.example.dispatcher.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.model.Article
import com.example.dispatcher.repository.ArticleRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepository = ArticleRepository(application)
    private val articlesLiveData = MutableLiveData<List<Article>>()

    private val articlesTitlesLiveData = MutableLiveData<String>()

    init {
        articlesLiveData.value = articleRepository.getArticles()
        updateTitles()
    }

    fun getFavoritesLiveData(): LiveData<String> {
        return articlesTitlesLiveData
    }

    private fun updateTitles() {
        val articlesList = articlesLiveData.value

        // Keep only the titles of the articles
        val articlesTitles = articlesList?.joinToString(separator = "\n") { article ->
            article.title
        } ?: "No articles found"

        articlesTitlesLiveData.value = articlesTitles
    }
}
