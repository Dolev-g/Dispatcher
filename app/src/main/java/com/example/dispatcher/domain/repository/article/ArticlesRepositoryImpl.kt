package com.example.dispatcher.domain.repository.article

import android.content.Context
import com.example.dispatcher.presentation.homepage.model.Article

class ArticlesRepositoryImpl(context: Context) : IArticleRepository {

    private val articles = mutableListOf<Article>()

    init {
        articles.add(Article(id = 1, title = "First Mock Article", content = "This is the content of the first mock article.", author = "John Doe"))
        articles.add(Article(id = 2, title = "Second Mock Article", content = "This is the content of the second mock article.", author = "Jane Smith"))
        articles.add(Article(id = 3, title = "Third Mock Article", content = "This is the content of the third mock article.", author = "Alice Johnson"))
        articles.add(Article(id = 4, title = "Fourth Mock Article", content = "This is the content of the fourth mock article.", author = "Bob Brown"))
    }

    override fun fetchArticles(): MutableList<Article> {
        return articles
    }
}
