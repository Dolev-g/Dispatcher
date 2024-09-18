package com.example.dispatcher.presentation.search.view

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
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
        binding.apply {
            headerSearchIcon.showView(false)
            headerXicon.showView(false)
            searchTextView.showView(false)
        }
    }

    private fun setListeners() {
        binding.searchEditText.addTextChangedListener { text ->
            binding.apply {
                if (text.isNullOrEmpty()) {
                    headerXicon.showView(false)
                    headerSearchIcon.showView(false)
                } else {
                    headerXicon.showView(true)
                }
            }
        }

        binding.headerXicon.setOnClickListener {
            binding.searchEditText.text?.clear()
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

    fun searchAction(action: (String) -> Unit) {
        binding.searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == 5 || actionId == EditorInfo.IME_ACTION_SEARCH || event?.keyCode == KeyEvent.KEYCODE_ENTER) {
                val query = binding.searchEditText.text.toString()
                if (query.isNotEmpty()) {
                    action(query)
                    searchQueryViewsActions(query)
                }
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun searchQueryViewsActions(query: String) {
        binding.apply {
            if (query.isNotEmpty()) {
                searchEditText.showView(false)
                searchTextView.showView(true)
                headerSearchIcon.showView(true)
                headerXicon.showView(false)
                searchTextView.text = "\"$query\""
            }
        }
    }
}

