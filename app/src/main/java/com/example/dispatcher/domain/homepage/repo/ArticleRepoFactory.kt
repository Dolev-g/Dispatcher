package com.example.dispatcher.domain.homepage.repo

import android.content.Context

class ArticleRepoFactory(private val context: Context) {

    fun createArticleRepo(type: EnumArticleType): InterfaceArticleRepo {
        if (type == EnumArticleType.MOCK) {
            return ArticleRepository(context)
        } else {
            throw IllegalArgumentException("Unknown repository type")
        }
    }

}
