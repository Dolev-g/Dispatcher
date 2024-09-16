package com.example.dispatcher.presentation.search.view

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.example.dispatcher.databinding.SearchHeaderBinding
import com.example.dispatcher.presentation.homepage.viewModel.ArticlesViewModel
import com.example.dispatcher.presentation.main.MainViewModel
import com.example.dispatcher.presentation.search.viewModel.SearchViewModel

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: SearchHeaderBinding = SearchHeaderBinding.inflate(LayoutInflater.from(context), this, true)
    private var mainViewModel: MainViewModel? = null
    private var articlesViewModel: ArticlesViewModel? = null
    private var searchViewModel: SearchViewModel? = null

    init {
        setListeners()
        binding.headerSearchIcon.visibility = View.GONE
        binding.headerXicon.visibility = View.GONE
        binding.searchTextView.visibility = View.GONE
    }

    private fun setListeners() {
        binding.backImg.setOnClickListener {
            mainViewModel?.changeSearchStage(false)
        }

        binding.searchEditText.addTextChangedListener { text ->
            if (text.isNullOrEmpty()) {
                binding.headerXicon.visibility = View.GONE
                binding.headerSearchIcon.visibility = View.GONE
            } else {
                binding.headerXicon.visibility = View.VISIBLE
            }
        }

        binding.searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event?.keyCode == KeyEvent.KEYCODE_ENTER) {
                val query = binding.searchEditText.text.toString()
                searchQuery(query)
                return@OnEditorActionListener true
            }
            false
        })

        binding.headerXicon.setOnClickListener {
            binding.searchEditText.text?.clear()
        }
    }

     fun searchQuery(q: String) {
        if (q.isNotEmpty()) {
            searchViewModel?.addSearchQuery(q)
            articlesViewModel?.fetchSearchArticles(q)

            binding.searchEditText.visibility = View.GONE
            binding.searchTextView.visibility = View.VISIBLE
            binding.headerSearchIcon.visibility = View.VISIBLE
            binding.headerXicon.visibility = View.GONE
            binding.searchTextView.text = "\"$q\""
        }
    }

    fun setViewModel(viewModel: MainViewModel) {
        this.mainViewModel = viewModel
    }

    fun setArticlesViewModel(viewModel: ArticlesViewModel) {
        this.articlesViewModel = viewModel
    }

    fun setSearchViewModel(viewModel: SearchViewModel) {
        this.searchViewModel = viewModel
    }
}

