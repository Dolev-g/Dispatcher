package com.example.dispatcher.presentation.homepage.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dispatcher.domain.repository.article.ArticleRepoFactory
import com.example.dispatcher.domain.repository.article.EnumApiType
import com.example.dispatcher.domain.repository.article.EnumArticleType
import com.example.dispatcher.domain.repository.article.IArticleRepository
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel
import kotlinx.coroutines.flow.Flow


class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepoFactory = ArticleRepoFactory(application)
    private val articlesRepository: IArticleRepository = articleRepoFactory.createArticleRepo(EnumArticleType.SERVER)

    fun fetchArticles(): Flow<PagingData<ArticleUiModel>> {
        return Pager(PagingConfig(pageSize = 5)) {
            articlesRepository.getArticlesPagingSource(EnumApiType.HEADLINES,"")
        }.flow.cachedIn(viewModelScope)
    }
//    val articlesLiveData = Pager(PagingConfig(pageSize = 5)) {
//        articlesRepository.getArticlesPagingSource(EnumApiType.HEADLINES,"")
//    }.flow.cachedIn(viewModelScope)

    fun fetchSearchArticles(query: String): Flow<PagingData<ArticleUiModel>> {
        return Pager(PagingConfig(pageSize = 5)) {
            articlesRepository.getArticlesPagingSource(EnumApiType.SEARCH, query)
        }.flow.cachedIn(viewModelScope)
    }

}
