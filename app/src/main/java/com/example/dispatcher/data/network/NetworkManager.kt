package com.example.dispatcher.data.network

import com.example.dispatcher.data.api.ApiConfigManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

object NetworkManager {

    private val baseUrl = ApiConfigManager.BASE_URL

    private val retrofit: Retrofit by lazy {

        val gson = GsonBuilder().setLenient().create()
        val client = OkHttpClient.Builder().build()


        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }
}
