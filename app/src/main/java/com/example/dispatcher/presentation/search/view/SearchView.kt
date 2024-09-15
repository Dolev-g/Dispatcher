package com.example.dispatcher.presentation.search.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dispatcher.databinding.SearchHeaderBinding
import com.example.dispatcher.presentation.homepage.viewModel.ArticlesViewModel
import com.example.dispatcher.presentation.main.MainViewModel

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: SearchHeaderBinding = SearchHeaderBinding.inflate(LayoutInflater.from(context), this, true)
    private var mainViewModel: MainViewModel? = null
    private var articlesViewModel: ArticlesViewModel? = null

    init {
        setListeners()
    }

    private fun setListeners() {
        binding.backImg.setOnClickListener {
            mainViewModel?.changeSearchStage(false)
        }

        binding.headerSearchIcon.setOnClickListener {
            searchQuery()
        }

        binding.searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event?.keyCode == KeyEvent.KEYCODE_ENTER) {
                searchQuery()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun searchQuery() {
        val query = binding.searchEditText.text.toString()
        Log.d("SearchView", "Search query: $query")
        articlesViewModel?.fetchSearchArticles(query)
    }


    fun setViewModel(viewModel: MainViewModel) {
        this.mainViewModel = viewModel
    }

    fun setArticlesViewModel(viewModel: ArticlesViewModel) {
        this.articlesViewModel = viewModel
    }
}
