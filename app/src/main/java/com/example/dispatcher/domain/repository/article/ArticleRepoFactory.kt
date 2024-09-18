package com.example.dispatcher.domain.repository.article

import android.content.Context

class ArticleRepoFactory(private val context: Context) {

    fun createArticleRepo(type: EnumArticleType): IArticleRepository {
        return when (type) {
//            EnumArticleType.MOCK -> MockArticleRepository(context)
            EnumArticleType.SERVER -> ArticlesRepositoryImpl()
            else -> throw IllegalArgumentException("Unknown repository type")
        }
    }
}
