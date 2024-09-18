package com.example.dispatcher.domain.repository.article

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

class ArticlesPagingSource(
    private val repository: IArticleRepository,
    private val apiType: EnumApiType
) : PagingSource<Int, ArticleUiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleUiModel> {
        return try {
            val nextPageNumber = params.key ?: 1 // Start loading from page 1 if key is null
            Log.d("PagingLog", "Loading page: $nextPageNumber")

            val articles: List<ArticleUiModel> = if (apiType == EnumApiType.HEADLINES) {
                repository.fetchArticlesPaged(nextPageNumber, 20)
            } else {
                repository.fetchSearchArticlesPaged(nextPageNumber, 20)
            }

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
        // Not needed for now, can be implemented if you have a way to track refresh keys
        return null
    }
}
