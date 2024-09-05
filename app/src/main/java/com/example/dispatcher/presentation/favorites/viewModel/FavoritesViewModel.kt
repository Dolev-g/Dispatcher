package com.example.dispatcher.presentation.favorites.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.domain.homepage.repo.ArticleRepoFactory
import com.example.dispatcher.presentation.homepage.model.Article
import com.example.dispatcher.domain.homepage.repo.ArticleRepository
import com.example.dispatcher.domain.homepage.repo.EnumArticleType
import com.example.dispatcher.domain.homepage.repo.InterfaceArticleRepo

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articleRepository: InterfaceArticleRepo = articleRepoFactory.createArticleRepo(EnumArticleType.MOCK)
    private var articlesList: MutableList<Article> = articleRepository.getArticles()

    private val articlesTitlesLiveData = MutableLiveData<String>()

    init {
        updateTitles()
    }

    fun getFavoritesLiveData(): LiveData<String> {
        return articlesTitlesLiveData
    }

    fun addTitle(title: String) {
        val currentTitles = articlesTitlesLiveData.value ?: ""
        val updatedTitles = if (currentTitles.isEmpty()) {
            title
        } else {
            "$currentTitles\n$title"
        }
        articlesTitlesLiveData.value = updatedTitles
    }

    private fun updateTitles() {
        var articlesTitles = ""

        articlesList?.forEach { article ->
            articlesTitles += "${article.title}\n"
        }

        if (articlesTitles.isEmpty()) {
            articlesTitles = "No articles found"
        } else {
            articlesTitles = articlesTitles.trimEnd()
        }

        articlesTitlesLiveData.value = articlesTitles
    }

}
