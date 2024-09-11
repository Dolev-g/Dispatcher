package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.domain.homepage.repo.ArticleRepoFactory
import com.example.dispatcher.presentation.homepage.model.Article
import com.example.dispatcher.domain.homepage.repo.EnumArticleType
import com.example.dispatcher.domain.homepage.repo.IArticleRepository

class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articleRepository: IArticleRepository = articleRepoFactory.createArticleRepo(EnumArticleType.MOCK)
    private var articlesList: MutableList<Article> = articleRepository.getArticles()

    private val firstTwoWordsLiveData = MutableLiveData<List<String>>()

    init {
        updateFirstTwoWordsArticles()
    }

    fun addFirstTwoWords(content: String) {
        val firstTwoWords = content.split(" ").take(2).joinToString(" ")
        val currentList = firstTwoWordsLiveData.value ?: emptyList()
        val updatedList = currentList + firstTwoWords
        firstTwoWordsLiveData.value = updatedList
    }

    fun getFirstTwoWordsLiveData(): LiveData<List<String>> {
        return firstTwoWordsLiveData
    }

    private fun updateFirstTwoWordsArticles() {
        val truncatedArticles = mutableListOf<String>()

        articlesList.forEach { article ->
            val firstTwoWords = article.content.split(" ").take(2).joinToString(" ")
            truncatedArticles.add(firstTwoWords)
        }

        firstTwoWordsLiveData.value = truncatedArticles
    }

}
