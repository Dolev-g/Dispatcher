package com.example.dispatcher.domain.repository.article

import android.content.Context
import com.example.dispatcher.R
import com.example.dispatcher.data.model.news.TopHeadlines
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

class MockArticleRepository(context: Context) : IArticleRepository {

    private val topHeadlinesLiveData = MutableLiveData<List<ArticleUiModel>>()
    private val searchHeadlinesLiveData = MutableLiveData<List<ArticleUiModel>>()
    private val allArticles = mutableListOf<ArticleUiModel>()

    init {
        val inputStream = context.resources.openRawResource(R.raw.mock_articles)
        val reader = InputStreamReader(inputStream)
        val topHeadlinesType = object : TypeToken<TopHeadlines>() {}.type
        val mockTopHeadlines: TopHeadlines = Gson().fromJson(reader, topHeadlinesType)
        allArticles.addAll(ArticleMapper.mapToUiModelList(mockTopHeadlines.articles))
        topHeadlinesLiveData.postValue(allArticles)
    }

    override fun fetchArticles(): LiveData<List<ArticleUiModel>> {
        return topHeadlinesLiveData
    }

    override fun fetchSearchArticles(query: String): LiveData<List<ArticleUiModel>> {
        val filteredArticles = allArticles.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.description?.contains(query, ignoreCase = true) == true
        }
        searchHeadlinesLiveData.postValue(filteredArticles)
        return searchHeadlinesLiveData
    }

}
