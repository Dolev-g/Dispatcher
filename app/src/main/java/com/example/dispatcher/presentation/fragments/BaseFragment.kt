package com.example.dispatcher.presentation.fragments

import com.example.dispatcher.data.JsonDataManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dispatcher.models.Article

abstract class BaseFragment : Fragment() {

    protected val articleList: ArrayList<Article> = ArrayList()

    protected open fun initArticleList() {
        val jsonDataManager = JsonDataManager(requireContext())
        val articlesFromJson: ArrayList<Article> = jsonDataManager.getArticlesFromJson()


        articleList.clear()
        articleList.addAll(articlesFromJson)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArticleList()
    }
}
