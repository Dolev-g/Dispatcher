package com.example.dispatcher.domain.repository.article

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

class ArticlesPagingSource(
    private val repository: IArticleRepository,
    private val apiType: EnumApiType,
    private val query: String
) : PagingSource<Int, ArticleUiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleUiModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            Log.d("PagingLog", "Loading page: $nextPageNumber")

            val articles: List<ArticleUiModel> = if (apiType == EnumApiType.HEADLINES) {
                repository.fetchArticlesPaged(nextPageNumber, 20)
            } else {
                repository.fetchArticlesPaged(nextPageNumber, 20)
            }

            Log.d("PagingLogSource", "articles: $articles")

            LoadResult.Page(
                data = articles,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleUiModel>): Int? {
        return null
    }
}
