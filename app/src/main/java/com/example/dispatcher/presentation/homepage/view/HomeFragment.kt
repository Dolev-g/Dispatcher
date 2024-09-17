package com.example.dispatcher.presentation.homepage.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dispatcher.R
import com.example.dispatcher.databinding.FragmentHomeBinding
import com.example.dispatcher.presentation.homepage.view.adapter.ArticleAdapter
import com.example.dispatcher.presentation.homepage.view.adapter.EnumArticleCardType
import com.example.dispatcher.presentation.homepage.view.adapter.TopSpacingItemDecoration
import com.example.dispatcher.presentation.homepage.viewModel.ArticlesViewModel
import com.example.dispatcher.presentation.main.view.MainActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val articlesViewModel: ArticlesViewModel by viewModels()
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeToArticles()

        binding.homeFilter.setFilterIconAction {
            (activity as? MainActivity)?.onFilterClick()
        }
    }

    private fun observeToArticles() {
        articlesViewModel.articlesLiveData.observe(viewLifecycleOwner) { articles ->
            if (articles != null) {
                articleAdapter.submitList(articles)
            }
        }
    }

    private fun initAdapter() {
        val recyclerView = binding.recyclerViewHomeFragment
        articleAdapter = ArticleAdapter(EnumArticleCardType.HOME)

        recyclerView.adapter = articleAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.recycler_item_spacing)
        recyclerView.addItemDecoration(TopSpacingItemDecoration(spacingInPixels))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
