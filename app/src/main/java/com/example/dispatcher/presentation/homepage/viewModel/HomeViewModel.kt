package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.presentation.homepage.model.Article
import com.example.dispatcher.domain.homepage.repo.ArticleRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepository = ArticleRepository(application)
    private lateinit var articlesList: MutableList<Article>


    private val firstTwoWordsLiveData = MutableLiveData<List<String>>()

    init {
        articlesList = articleRepository.getArticles().toMutableList()
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
        val truncatedArticles = articlesList?.map { article ->
            article.content.split(" ").take(2).joinToString(" ")
        } ?: emptyList()
        firstTwoWordsLiveData.value = truncatedArticles
    }
}
