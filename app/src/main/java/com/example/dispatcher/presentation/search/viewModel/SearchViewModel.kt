package com.example.dispatcher.presentation.search.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SearchViewModel(application: Application)  : AndroidViewModel(application) {

    private val _searchHistory = MutableLiveData<List<String>>()
    val searchHistory: LiveData<List<String>> get() = _searchHistory

    init {
        _searchHistory.value = emptyList()
    }

    fun addSearchQuery(query: String) {
        val currentHistory = _searchHistory.value.orEmpty().toMutableList()
        currentHistory.add(query)
        _searchHistory.value = currentHistory
    }

    fun deleteSearchQuery(query: String) {
        val currentHistory = _searchHistory.value.orEmpty().toMutableList()
        currentHistory.remove(query)
        _searchHistory.value = currentHistory
    }

    fun clearSearchHistory() {
        _searchHistory.value = emptyList()
    }
}



