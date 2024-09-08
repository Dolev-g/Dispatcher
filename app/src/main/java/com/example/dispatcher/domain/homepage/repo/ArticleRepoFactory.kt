package com.example.dispatcher.domain.homepage.repo

import android.content.Context

class ArticleRepoFactory(private val context: Context) {

    fun createArticleRepo(type: EnumArticleType): IArticleRepository {
        if (type == EnumArticleType.MOCK) {
            return MockArticleRepository(context)
        } else {
            throw IllegalArgumentException("Unknown repository type")
        }
    }

}
