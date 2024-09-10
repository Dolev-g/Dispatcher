package com.example.dispatcher.domain.repository.article

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.data.api.ApiConfigManager
import com.example.dispatcher.data.api.Secrets
import com.example.dispatcher.data.api.news.NewsServiceApi
import com.example.dispatcher.data.model.news.TopHeadlines
import com.example.dispatcher.data.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesRepositoryImpl(context: Context) : IArticleRepository {

    private val newsServiceApi = NetworkManager.createService(NewsServiceApi::class.java)

    private val topHeadlinesLiveData = MutableLiveData<TopHeadlines?>()

    override fun fetchArticles(): LiveData<TopHeadlines?> {
        newsServiceApi.getTopHeadlines(ApiConfigManager.getCountryCode(), Secrets.API_KEY).enqueue(object : Callback<TopHeadlines> {
            override fun onResponse(call: Call<TopHeadlines>, response: Response<TopHeadlines>) {
                if (response.isSuccessful) {
                    response.body()?.let { topHeadlines ->

                        topHeadlinesLiveData.postValue(topHeadlines)
                    } ?: run {
                        topHeadlinesLiveData.postValue(null)
                    }
                } else {
                    topHeadlinesLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<TopHeadlines>, t: Throwable) {
                topHeadlinesLiveData.postValue(null)
            }
        })

        return topHeadlinesLiveData
    }
}