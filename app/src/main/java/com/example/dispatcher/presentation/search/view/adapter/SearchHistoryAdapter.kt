package com.example.dispatcher.presentation.search.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispatcher.databinding.ItemSearchHistoryBinding
import com.example.dispatcher.presentation.search.view.SearchFragment
import com.example.dispatcher.presentation.search.view.SearchView
import com.example.dispatcher.presentation.search.viewModel.SearchViewModel

class SearchHistoryAdapter(
    private val searchViewModel: SearchViewModel,
    private val searchFragment: SearchFragment
) : RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>() {

    private var searchHistory: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val binding = ItemSearchHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchHistoryViewHolder(binding, searchViewModel, searchFragment)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val searchQuery = searchHistory[position]
        holder.bind(searchQuery, position)
    }

    override fun getItemCount(): Int = searchHistory.size

    fun updateSearchHistory(newHistory: List<String>) {
        searchHistory = newHistory
        notifyDataSetChanged()
    }

    class SearchHistoryViewHolder(
        private val binding: ItemSearchHistoryBinding,
        private val searchViewModel: SearchViewModel,
        private val searchFragment: SearchFragment
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(searchQuery: String, position: Int) {
            binding.searchQueryTextView.text = searchQuery

            binding.searchQueryTextView.setOnClickListener {
                searchFragment.searchAction(searchQuery)
            }

            binding.deleteImg.setOnClickListener {
                searchViewModel.deleteSearchQueryByIndex(position)
            }
        }
    }
}
