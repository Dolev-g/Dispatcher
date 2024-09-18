package com.example.dispatcher.presentation.homepage.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

class ArticleDiffCallback : DiffUtil.ItemCallback<ArticleUiModel>() {
    override fun areItemsTheSame(oldItem: ArticleUiModel, newItem: ArticleUiModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ArticleUiModel, newItem: ArticleUiModel): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.description == newItem.description &&
                oldItem.urlToImage == newItem.urlToImage &&
                oldItem.author == newItem.author &&
                oldItem.publishedAt == newItem.publishedAt
    }
}