package com.example.dispatcher.presentation.profile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.presentation.homepage.model.Article
import com.example.dispatcher.domain.homepage.repo.ArticleRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepository = ArticleRepository(application)
    private lateinit var articlesList: MutableList<Article>

    private val authorsLiveData = MutableLiveData<String>()

    init {
        articlesList = articleRepository.getArticles().toMutableList()
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

        // Keep only the authors of the articles
        val authors = articlesList?.joinToString(separator = "\n") { article ->
            article.author
        } ?: "No authors found"

        authorsLiveData.value = authors
    }
}
