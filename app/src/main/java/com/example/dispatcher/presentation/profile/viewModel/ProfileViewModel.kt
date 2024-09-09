package com.example.dispatcher.presentation.profile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.dispatcher.domain.repository.article.ArticleRepoFactory
import com.example.dispatcher.domain.repository.article.EnumArticleType
import com.example.dispatcher.domain.repository.article.IArticleRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articleRepository: IArticleRepository = articleRepoFactory.createArticleRepo(EnumArticleType.MOCK)


    init {
    }



}
