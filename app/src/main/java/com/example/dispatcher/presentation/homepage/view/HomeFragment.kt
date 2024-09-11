package com.example.dispatcher.presentation.homepage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dispatcher.databinding.FragmentHomeBinding
import com.example.dispatcher.presentation.homepage.viewModel.ArticlesViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val articlesViewModel: ArticlesViewModel by viewModels()

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
        observeToArticles()
    }

    private fun observeToArticles () {
        articlesViewModel.articlesLiveData.observe(viewLifecycleOwner) { topHeadlines ->
            val articlesList = topHeadlines?.articles
            var articlesText: String = ""

            if (articlesList == null) {
                articlesText = "unable to load articles"
            } else {
                articlesText = articlesList.joinToString(separator = "\n") { article ->
                    "Title: ${article.title}, Author: ${article.author}"
                }
            }

            binding.textViewHomeFragment.text = articlesText
        }
    }

    override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
    }
}
