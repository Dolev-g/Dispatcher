package com.example.dispatcher

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

abstract class BaseFragment : Fragment() {

    protected val articleList: ArrayList<Article> = ArrayList()

    protected open fun initArticleList() {
        val jsonFileInputStream = resources.openRawResource(R.raw.articles)
        val reader = InputStreamReader(jsonFileInputStream)
        val articleType = object : TypeToken<ArrayList<Article>>() {}.type
        val articlesFromJson: ArrayList<Article> = Gson().fromJson(reader, articleType)

        articleList.clear()
        articleList.addAll(articlesFromJson)

        reader.close()
        jsonFileInputStream.close()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArticleList()
    }
}
