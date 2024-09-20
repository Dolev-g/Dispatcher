package com.example.dispatcher.presentation.search.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SearchViewModel(application: Application)  : AndroidViewModel(application) {

    private val _searchHistory = MutableLiveData<List<String>>()
    val searchHistory: LiveData<List<String>> get() = _searchHistory

    private var lastQuery = ""

    init {
        _searchHistory.value = emptyList()
    }

    fun addSearchQuery(query: String) {
        changeLastQuery(query)
        val currentHistory = _searchHistory.value.orEmpty().toMutableList()
        currentHistory.add(query)
        _searchHistory.value = currentHistory
    }

    fun deleteSearchQueryByIndex(index: Int) {
        val currentHistory = _searchHistory.value.orEmpty().toMutableList()
        if (index in currentHistory.indices) {
            currentHistory.removeAt(index)
            _searchHistory.value = currentHistory
        }
    }

    fun clearSearchHistory() {
        _searchHistory.value = emptyList()
    }

    private fun changeLastQuery(query:String) {
        lastQuery = query
    }

    fun getLastQuery(): String {
        return lastQuery
    }
}



