package com.example.dispatcher.domain.repository.article

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.data.api.Secrets
import com.example.dispatcher.data.api.news.NewsApi
import com.example.dispatcher.data.api.news.NewsServiceApi
import com.example.dispatcher.data.model.news.TopHeadlines
import com.example.dispatcher.data.network.NetworkManager
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesRepositoryImpl(context: Context) : IArticleRepository {

    private val newsServiceApi = NetworkManager.createService(NewsServiceApi::class.java)
    private val topHeadlinesLiveData = MutableLiveData<List<ArticleUiModel>>()
    private val searchHeadlinesLiveData = MutableLiveData<List<ArticleUiModel>>()

    override fun fetchArticles(): LiveData<List<ArticleUiModel>> {
        newsServiceApi.getTopHeadlines(NewsApi.COUNTRY_CODE, Secrets.API_KEY).enqueue(object : Callback<TopHeadlines> {
            override fun onResponse(call: Call<TopHeadlines>, response: Response<TopHeadlines>) {
                if (response.isSuccessful) {
                    response.body()?.let { topHeadlines ->
                        val articleUiModels = ArticleMapper.mapToUiModelList(topHeadlines.articles)
                        topHeadlinesLiveData.postValue(articleUiModels)
                    } ?: onFailure(call, Throwable("Empty response body"))
                } else {
                    onFailure(call, Throwable("Response not successful: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<TopHeadlines>, t: Throwable) {
                topHeadlinesLiveData.postValue(emptyList())
            }
        })

        return topHeadlinesLiveData
    }

    override fun fetchSearchArticles(query: String): LiveData<List<ArticleUiModel>> {
        newsServiceApi.getSearchArticles(query, Secrets.API_KEY).enqueue(object : Callback<TopHeadlines> {
            override fun onResponse(call: Call<TopHeadlines>, response: Response<TopHeadlines>) {
                if (response.isSuccessful) {
                    response.body()?.let { topHeadlines ->
                        val articleUiModels = ArticleMapper.mapToUiModelList(topHeadlines.articles)
                        searchHeadlinesLiveData.postValue(articleUiModels)
                    } ?: onFailure(call, Throwable("Empty response body"))
                } else {
                    onFailure(call, Throwable("Response not successful: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<TopHeadlines>, t: Throwable) {
                searchHeadlinesLiveData.postValue(emptyList())
            }
        })

        return searchHeadlinesLiveData
    }

}
