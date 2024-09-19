package com.example.dispatcher.data.network

import com.example.dispatcher.data.api.news.NewsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor

object NetworkManager {

    private val baseUrl = NewsApi.BASE_URL

    private val retrofit: Retrofit by lazy {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val gson = GsonBuilder().setLenient().create()
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

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
