package com.example.dispatcher.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.model.Article
import com.example.dispatcher.repository.ArticleRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepository = ArticleRepository(application)
    private lateinit var articlesList: MutableList<Article>

    private val articlesTitlesLiveData = MutableLiveData<String>()

    init {
        articlesList = articleRepository.getArticles().toMutableList()
        updateTitles()
    }

    fun getFavoritesLiveData(): LiveData<String> {
        return articlesTitlesLiveData
    }

    fun addTitle(title: String) {
        val currentTitles = articlesTitlesLiveData.value ?: ""
        // Add the new title to the existing titles, separated by a newline
        val updatedTitles = if (currentTitles.isEmpty()) {
            title
        } else {
            "$currentTitles\n$title"
        }
        articlesTitlesLiveData.value = updatedTitles
    }

    private fun updateTitles() {

        // Keep only the titles of the articles
        val articlesTitles = articlesList?.joinToString(separator = "\n") { article ->
            article.title
        } ?: "No articles found"

        articlesTitlesLiveData.value = articlesTitles
    }
}
