package com.example.dispatcher.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.model.Article
import com.example.dispatcher.repository.ArticleRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepository = ArticleRepository(application)
    private val articlesLiveData = MutableLiveData<List<Article>>()

    private val authorsLiveData = MutableLiveData<String>()

    init {
        articlesLiveData.value = articleRepository.getArticles()
        updateAuthors()
    }

    fun getAuthorsLiveData(): LiveData<String> {
        return authorsLiveData
    }

    fun addAuthor(author: String) {
        val currentTitles = authorsLiveData.value ?: ""
        // Add the new title to the existing titles, separated by a newline
        val updatedTitles = if (currentTitles.isEmpty()) {
            author
        } else {
            "$currentTitles\n$author"
        }
        authorsLiveData.value = updatedTitles
    }

    private fun updateAuthors() {
        val articlesList = articlesLiveData.value

        // Keep only the authors of the articles
        val authors = articlesList?.joinToString(separator = "\n") { article ->
            article.author
        } ?: "No authors found"

        authorsLiveData.value = authors
    }
}
