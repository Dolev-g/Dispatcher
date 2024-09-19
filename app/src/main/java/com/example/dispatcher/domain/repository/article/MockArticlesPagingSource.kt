package com.example.dispatcher.domain.repository.article

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dispatcher.data.model.news.TopHeadlines
import com.example.dispatcher.domain.repository.article.ArticleMapper.mapToUiModelList
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

class MockArticlesPagingSource(
    private val repository: IArticleRepository,
    private val apiType: EnumApiType,
    private val query: String,
) : PagingSource<Int, ArticleUiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleUiModel> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val filteredArticles = if (apiType == EnumApiType.HEADLINES) {
                repository.fetchArticlesPaged(page,20)
            } else {
                repository.fetchSearchArticlesPaged(query, page, 20)
            }

            val start = (page - 1) * pageSize
            val end = minOf(start + pageSize, filteredArticles.size)

            Log.d("PagingLogSource", "articles: $filteredArticles")

            LoadResult.Page(
                data = filteredArticles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (end < filteredArticles.size) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleUiModel>): Int? {
        return null
    }
}