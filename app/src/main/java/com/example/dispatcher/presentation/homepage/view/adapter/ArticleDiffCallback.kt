package com.example.dispatcher.presentation.homepage.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.dispatcher.presentation.homepage.model.ArticleView

class ArticleDiffCallback : DiffUtil.ItemCallback<ArticleView>() {
    override fun areItemsTheSame(oldItem: ArticleView, newItem: ArticleView): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ArticleView, newItem: ArticleView): Boolean {
        return oldItem == newItem
    }
}