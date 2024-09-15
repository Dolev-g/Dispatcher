package com.example.dispatcher.domain.repository.article

import android.content.Context
import com.example.dispatcher.R
import com.example.dispatcher.data.model.news.TopHeadlines
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
class MockArticleRepository(context: Context) : IArticleRepository {

    private val topHeadlinesLiveData = MutableLiveData<TopHeadlines>()
    private val searchHeadlinesLiveData = MutableLiveData<TopHeadlines?>()

    init {
        val inputStream = context.resources.openRawResource(R.raw.mock_articles)
        val reader = InputStreamReader(inputStream)
        val topHeadlinesType = object : TypeToken<TopHeadlines>() {}.type
        val mockTopHeadlines: TopHeadlines = Gson().fromJson(reader, topHeadlinesType)

        topHeadlinesLiveData.postValue(
            TopHeadlines(
                status = mockTopHeadlines.status,
                totalResults = mockTopHeadlines.totalResults,
                articles = ArrayList(mockTopHeadlines.articles)
            )
        )

    }

    override fun fetchArticles(): LiveData<TopHeadlines?> {
        return topHeadlinesLiveData
    }

    override fun fetchSearchArticles(q: String): LiveData<TopHeadlines?> {
        val currentHeadlines = topHeadlinesLiveData.value

        val filteredArticles = currentHeadlines?.articles?.filter { article ->
            article.title.contains(q, ignoreCase = true) || article.description.contains(q, ignoreCase = true)
        }

        val searchResults = filteredArticles?.let {
            TopHeadlines(
                status = currentHeadlines.status,
                totalResults = it.size,
                articles = ArrayList(it)
            )
        }

        searchHeadlinesLiveData.postValue(searchResults)
        return searchHeadlinesLiveData
    }

}
