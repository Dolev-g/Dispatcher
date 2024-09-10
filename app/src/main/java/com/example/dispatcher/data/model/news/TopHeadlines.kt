package com.example.dispatcher.data.model.news

data class TopHeadlines(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Article>
)
