package com.example.dispatcher.common.utils

import android.content.Context
import com.example.dispatcher.features.homepage.domain.models.Article
import com.example.dispatcher.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class JsonDataManager(private val context: Context) {

    fun getArticlesFromJson(): ArrayList<Article> {
        val jsonFileInputStream = context.resources.openRawResource(R.raw.articles)
        val reader = InputStreamReader(jsonFileInputStream)
        val articleType = object : TypeToken<ArrayList<Article>>() {}.type
        return Gson().fromJson(reader, articleType)
    }
}
