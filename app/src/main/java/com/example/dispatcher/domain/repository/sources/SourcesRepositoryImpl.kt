package com.example.dispatcher.domain.repository.sources

import android.util.Log
import com.example.dispatcher.data.api.Secrets
import com.example.dispatcher.data.api.news.NewsServiceApi
import com.example.dispatcher.data.network.NetworkManager

class SourcesRepositoryImpl : ISourcesRepository {

    private val newsServiceApi = NetworkManager.createService(NewsServiceApi::class.java)
    private val mock: Boolean = true

    override suspend fun getSourceNames(): List<String> {

        if (mock) {
            val newsSources = listOf(
                "ESPN",
                "Associated Press",
                "Reuters",
                "CNN"
            )
            return newsSources
        }
        else{
            val apiKey = Secrets.API_KEY
            val response = newsServiceApi.getSources(apiKey)
            Log.i("getSourceNames", "getSourceNames: ")
            return response.sources.map { it.name }
        }
    }
}