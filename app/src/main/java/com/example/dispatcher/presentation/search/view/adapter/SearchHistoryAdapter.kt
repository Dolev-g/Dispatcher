package com.example.dispatcher.presentation.search.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispatcher.databinding.ItemSearchHistoryBinding
import com.example.dispatcher.presentation.search.view.SearchView
import com.example.dispatcher.presentation.search.viewModel.SearchViewModel

class SearchHistoryAdapter(
    private val searchViewModel: SearchViewModel,
    private val searchView: SearchView
) : RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>() {

    private var searchHistory: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val binding = ItemSearchHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchHistoryViewHolder(binding, searchViewModel, searchView)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val searchQuery = searchHistory[position]
        holder.bind(searchQuery)
    }

    override fun getItemCount(): Int = searchHistory.size

    fun updateSearchHistory(newHistory: List<String>) {
        searchHistory = newHistory
        notifyDataSetChanged()
    }

    class SearchHistoryViewHolder(
        private val binding: ItemSearchHistoryBinding,
        private val searchViewModel: SearchViewModel,
        private val searchView: SearchView
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(searchQuery: String) {
            binding.searchQueryTextView.text = searchQuery

            binding.searchQueryTextView.setOnClickListener {
                Log.d("SearchHistoryAdapter", "Search query clicked: $searchQuery") // Log the search query
                searchView.searchQuery(searchQuery)
            }

            binding.deleteImg.setOnClickListener {
                searchViewModel.deleteSearchQuery(searchQuery)
            }
        }
    }
}
