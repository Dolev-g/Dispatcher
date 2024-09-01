package com.example.dispatcher

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dispatcher.Article

abstract class BaseFragment : Fragment() {

    // ArrayList to hold Article objects
    protected val articleList: ArrayList<Article> = ArrayList()

    // Function to initialize the articleList with three Article objects
    protected open fun initArticleList() {
        // Creating three Article objects
        val article1 = Article(
            title = "First Article",
            imageUrl = null, // Image URL is null as specified
            author = "Author One",
            body = "This is1 the body of the first article."
        )

        val article2 = Article(
            title = "Second Article",
            imageUrl = null,
            author = "Author Two",
            body = "This is2 the body of the second article."
        )

        val article3 = Article(
            title = "Third Article",
            imageUrl = null,
            author = "Author Three",
            body = "This is3 the body of the third article."
        )

        // Adding articles to the articleList
        articleList.add(article1)
        articleList.add(article2)
        articleList.add(article3)
    }

    // Override onViewCreated to call initArticleList
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArticleList() // Initialize the article list when the view is created
    }
}
