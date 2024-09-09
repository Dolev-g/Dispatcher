package com.example.dispatcher.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

object NetworkManager {

    private const val BASE_URL = "https://newsapi.org/"

    private val retrofit: Retrofit by lazy {

        val gson = GsonBuilder().setLenient().create()
        val client = OkHttpClient.Builder().build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }
}
