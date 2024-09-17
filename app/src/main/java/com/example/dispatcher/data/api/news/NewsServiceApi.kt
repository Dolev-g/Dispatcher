package com.example.dispatcher.data.api.news

import com.example.dispatcher.data.model.news.TopHeadlines
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi {

    @GET(NewsApi.TOP_HEADLINES_ENDPOINT)
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<TopHeadlines>

    @GET(NewsApi.SEARCH_API)
    fun getSearchArticles(
        @Query("q") searchQuery: String,
        @Query("apiKey") apiKey: String
    ): Call<TopHeadlines>


}
