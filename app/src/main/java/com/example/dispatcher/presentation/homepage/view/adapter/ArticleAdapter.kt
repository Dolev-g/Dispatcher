package com.example.dispatcher.presentation.homepage.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dispatcher.R
import com.example.dispatcher.databinding.AdapterArticleItemBinding
import com.example.dispatcher.presentation.homepage.model.ArticleUiModel

class ArticleAdapter : ListAdapter<ArticleUiModel, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallback()) {

    class ArticleViewHolder(private val binding: AdapterArticleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticleUiModel) {
            binding.title.text = article.title
            binding.body.text = article.description
            binding.cardAuthor.text = article.author
            binding.date.text = article.publishedAt

            Glide.with(binding.root.context)
                .load(article.urlToImage)
                 .placeholder(R.drawable.placeholder)
                .into(binding.headerImage)

            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, article.title, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = AdapterArticleItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }
}
