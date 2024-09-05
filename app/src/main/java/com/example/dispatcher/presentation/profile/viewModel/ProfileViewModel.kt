package com.example.dispatcher.presentation.profile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.domain.homepage.repo.ArticleRepoFactory
import com.example.dispatcher.presentation.homepage.model.Article
import com.example.dispatcher.domain.homepage.repo.ArticleRepository
import com.example.dispatcher.domain.homepage.repo.EnumArticleType
import com.example.dispatcher.domain.homepage.repo.InterfaceArticleRepo

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articleRepository: InterfaceArticleRepo = articleRepoFactory.createArticleRepo(EnumArticleType.MOCK)
    private var articlesList: MutableList<Article> = articleRepository.getArticles()

    private val authorsLiveData = MutableLiveData<String>()

    init {
        updateAuthors()
    }

    fun getAuthorsLiveData(): LiveData<String> {
        return authorsLiveData
    }

    fun addAuthor(author: String) {
        val currentAuthors = authorsLiveData.value ?: ""
        val updatedTitles = if (currentAuthors.isEmpty()) {
            author
        } else {
            "$currentAuthors\n$author"
        }
        authorsLiveData.value = updatedTitles
    }

    private fun updateAuthors() {

        var authors = ""
        articlesList?.forEach { article ->
            authors += "${article.author}\n"
        }

        if (authors.isEmpty()) {
            authors = "No authors found"
        }

        authorsLiveData.value = authors.trimEnd()
    }

}
