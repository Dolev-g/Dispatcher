package com.example.dispatcher.domain.repository.article

import com.example.dispatcher.data.model.news.Article
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

object ArticleMapper {

    private fun mapToUiModel(article: Article): ArticleUiModel {
        return ArticleUiModel(
            title = article.title,
            description = article.description,
            urlToImage = article.urlToImage,
            author = article.author,
            publishedAt = article.publishedAt,
            source = article.source.name
        )
    }

    fun mapToUiModelList(articles: List<Article>): List<ArticleUiModel> {
        return articles.map { mapToUiModel(it) }
    }
}