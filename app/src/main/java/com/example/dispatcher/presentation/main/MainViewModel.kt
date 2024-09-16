package com.example.dispatcher.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _searchStage = MutableLiveData<Boolean>()
    val searchStage: LiveData<Boolean> get() = _searchStage

    private val _filterDrawerDisplay = MutableLiveData<Boolean>()
    val filterDrawerDisplay: LiveData<Boolean> get() = _filterDrawerDisplay

    init {
        _searchStage.value = false
        _filterDrawerDisplay.value = false
    }

    fun changeSearchStage(newState: Boolean) {
        _searchStage.value = newState
    }

    fun changeFilterDrawerDisplay(newState: Boolean) {
        _filterDrawerDisplay.value = newState
    }
}
