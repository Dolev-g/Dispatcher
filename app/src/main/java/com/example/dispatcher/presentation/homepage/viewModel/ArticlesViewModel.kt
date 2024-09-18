package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.dispatcher.domain.repository.article.ArticleRepoFactory
import com.example.dispatcher.domain.repository.article.EnumApiType
import com.example.dispatcher.domain.repository.article.EnumArticleType
import com.example.dispatcher.domain.repository.article.IArticleRepository


class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articlesRepository: IArticleRepository = articleRepoFactory.createArticleRepo(EnumArticleType.SERVER)

    val articlesLiveData = Pager(PagingConfig(pageSize = 20)) {
        articlesRepository.getArticlesPagingSource(EnumApiType.HEADLINES)
    }.flow.cachedIn(viewModelScope)

    val searchArticlesLiveData = Pager(PagingConfig(pageSize = 20)) {
        articlesRepository.getArticlesPagingSource(EnumApiType.SEARCH)
    }.flow.cachedIn(viewModelScope)


}
