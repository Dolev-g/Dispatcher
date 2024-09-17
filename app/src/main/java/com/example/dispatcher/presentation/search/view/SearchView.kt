package com.example.dispatcher.presentation.search.view

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.example.dispatcher.common.utils.showView
import com.example.dispatcher.databinding.SearchHeaderBinding
import com.example.dispatcher.presentation.homepage.viewModel.ArticlesViewModel
import com.example.dispatcher.presentation.search.viewModel.SearchViewModel

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: SearchHeaderBinding = SearchHeaderBinding.inflate(LayoutInflater.from(context), this, true)
    private var articlesViewModel: ArticlesViewModel? = null
    private var searchViewModel: SearchViewModel? = null

    init {
        setListeners()
        binding.headerSearchIcon.showView(false)
        binding.headerXicon.showView(false)
        binding.searchTextView.showView(false)
    }

    private fun setListeners() {

        binding.searchEditText.addTextChangedListener { text ->
            if (text.isNullOrEmpty()) {
                binding.headerXicon.showView(false)
                binding.headerSearchIcon.showView(false)
            } else {
                binding.headerXicon.showView(true)
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

     fun searchQuery(query: String) {
        if (query.isNotEmpty()) {
            searchViewModel?.addSearchQuery(query)
            articlesViewModel?.fetchSearchArticles(query)

            binding.searchEditText.showView(false)
            binding.searchTextView.showView(true)
            binding.headerSearchIcon.showView(true)
            binding.headerXicon.showView(false)
            binding.searchTextView.text = "\"$query\""
        }
    }

    fun setArticlesViewModel(viewModel: ArticlesViewModel) {
        this.articlesViewModel = viewModel
    }

    fun setSearchViewModel(viewModel: SearchViewModel) {
        this.searchViewModel = viewModel
    }

    fun setBackAction(action: () -> Unit) {
        binding.backImg.setOnClickListener {
            action()
        }
    }
}

