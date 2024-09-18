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
import com.example.dispatcher.common.utils.showView
import com.example.dispatcher.databinding.FragmentSearchBinding
import com.example.dispatcher.presentation.homepage.view.adapter.ArticleAdapter
import com.example.dispatcher.presentation.homepage.view.adapter.EnumArticleCardType
import com.example.dispatcher.presentation.homepage.view.adapter.TopSpacingItemDecoration
import com.example.dispatcher.presentation.homepage.viewModel.ArticlesViewModel
import com.example.dispatcher.presentation.main.view.MainActivity
import com.example.dispatcher.presentation.search.view.adapter.SearchHistoryAdapter
import com.example.dispatcher.presentation.search.viewModel.SearchViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

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
        binding.apply {
            searchView.setArticlesViewModel(articlesViewModel)
            searchView.setSearchViewModel(searchViewModel)
            resultsLayout.showView(false)
            notFoundLayout.showView(false)
        }

        initAdapter()
        initSearchHistoryAdapter()

        observeSearchHistory()
        observeToSearchArticles()
        setListeners()
    }

    private fun observeToSearchArticles() {
        articlesViewModel.searchArticlesLiveData.observe(viewLifecycleOwner) { articles ->

            if (articles != null) {
                if (articles.isNotEmpty()) {
                    articleAdapter.submitList(articles)
                    binding.apply {
                        resultsLayout.showView(true)
                        recentSearchesLayout.showView(false)
                    }
                }
                else {
                    binding.apply {
                        notFoundLayout.showView(true)
                        recentSearchesLayout.showView(false)
                    }
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

    private fun initAdapter() {
        binding.recyclerViewSearch.let { recyclerView ->
            articleAdapter = ArticleAdapter(EnumArticleCardType.SEARCH).also {
                recyclerView.adapter = it
            }
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.recycler_item_spacing)
            recyclerView.addItemDecoration(TopSpacingItemDecoration(spacingInPixels))
        }

    }

    private fun initSearchHistoryAdapter() {
        binding.recyclerViewSearchHistory.let { recyclerView ->
            searchHistoryAdapter = SearchHistoryAdapter(searchViewModel, this).also {
                recyclerView.adapter = it
            }
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setListeners() {
        binding.apply {
            clearButton.setOnClickListener {
                searchViewModel.clearSearchHistory()
            }

            searchView.setBackAction {
                (context as? MainActivity)?.onBackClick()
            }

            filter.setFilterIconAction {
                (context as? MainActivity)?.onFilterClick()
            }

            searchView.searchAction { query ->
                searchAction(query)
            }
        }
    }

    fun searchAction(query: String) {
        searchViewModel.addSearchQuery(query)
        articlesViewModel.fetchSearchArticles(query)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}