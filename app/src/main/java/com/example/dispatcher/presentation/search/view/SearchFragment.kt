package com.example.dispatcher.presentation.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dispatcher.R
import com.example.dispatcher.databinding.FragmentSearchBinding
import com.example.dispatcher.presentation.homepage.view.adapter.ArticleAdapter
import com.example.dispatcher.presentation.homepage.view.adapter.TopSpacingItemDecoration
import com.example.dispatcher.presentation.homepage.viewModel.ArticlesViewModel
import com.example.dispatcher.presentation.main.MainViewModel
import com.example.dispatcher.presentation.search.view.adapter.SearchHistoryAdapter
import com.example.dispatcher.presentation.search.viewModel.SearchViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()
    private val articlesViewModel: ArticlesViewModel by viewModels()
    private val searchViewModel: SearchViewModel by activityViewModels()

    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var searchHistoryAdapter: SearchHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setViewModel(mainViewModel)
        binding.searchView.setArticlesViewModel(articlesViewModel)
        binding.searchView.setSearchViewModel(searchViewModel)

        binding.resultsLayout.visibility = View.GONE
        binding.notFoundLayout.visibility = View.GONE
        observeToSearchArticles()
        addAdapter()
        addSearchHistoryAdapter()
        observeSearchHistory()
    }

    private fun observeToSearchArticles() {
        articlesViewModel.searchArticlesLiveData.observe(viewLifecycleOwner) { articles ->

            if (articles != null) {
                if (articles.size != 0) {
                    articleAdapter.submitList(articles)
                    binding.resultsLayout.visibility = View.VISIBLE
                    binding.recentSearchesLayout.visibility = View.GONE
                }
                else {
                    binding.notFoundLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun observeSearchHistory() {
        searchViewModel.searchHistory.observe(viewLifecycleOwner) { queries ->
            if (queries != null) {
                searchHistoryAdapter.updateSearchHistory(queries)
            }
        }
    }

    private fun addAdapter() {
        val recyclerView = binding.recyclerViewSearch
        articleAdapter = ArticleAdapter()

        recyclerView.adapter = articleAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.recycler_item_spacing)
        recyclerView.addItemDecoration(TopSpacingItemDecoration(spacingInPixels))
    }

    private fun addSearchHistoryAdapter() {
        val recyclerView = binding.recyclerViewSearchHistory
        searchHistoryAdapter = SearchHistoryAdapter(searchViewModel)

        recyclerView.adapter = searchHistoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}