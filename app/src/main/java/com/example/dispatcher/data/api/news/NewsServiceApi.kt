package com.example.dispatcher.data.api.news

import com.example.dispatcher.data.model.news.TopHeadlines
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi {

    @GET(NewsApi.TOP_HEADLINES_ENDPOINT)
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): TopHeadlines

    @GET(NewsApi.SEARCH_API)
    suspend fun getSearchArticles(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): TopHeadlines

    @GET(NewsApi.TOP_HEADLINES_ENDPOINT)
    suspend fun getTopHeadlinesPaged(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): TopHeadlines

    @GET(NewsApi.SEARCH_API)
    suspend fun getSearchHeadlinesPaged(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): TopHeadlines
}
