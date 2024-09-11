package com.example.dispatcher.presentation.homepage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dispatcher.R
import com.example.dispatcher.databinding.FragmentHomeBinding
import com.example.dispatcher.presentation.homepage.model.ArticleView
import com.example.dispatcher.presentation.homepage.view.adapter.ArticleAdapter
import com.example.dispatcher.presentation.homepage.view.adapter.TopSpacingItemDecoration
import com.example.dispatcher.presentation.homepage.viewModel.ArticlesViewModel

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
        addAdapter()
        observeToArticles()
    }

    private fun observeToArticles () {
        articlesViewModel.articlesLiveData.observe(viewLifecycleOwner) { topHeadlines ->
            val articlesList = topHeadlines?.articles

            if (articlesList != null) {
                val articlesViewList = mutableListOf<ArticleView>()

                articlesList.forEach { article ->
                    val articleView = ArticleView(
                        title = article.title,
                        description = article.description ?: "No description available",
                        urlToImage = article.urlToImage ?: "default_image_url",
                        author = article.author ?: "unknown author",
                        publishedAt = article.publishedAt ?: "unknown data"
                    )
                    articlesViewList.add(articleView)
                }

                articleAdapter.submitList(articlesViewList)
            }
        }
    }

    private fun addAdapter() {
        val recyclerView = binding.recyclerViewHomeFragment
        articleAdapter = ArticleAdapter()

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
