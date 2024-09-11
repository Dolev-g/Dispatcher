package com.example.dispatcher.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _searchStage = MutableLiveData<Boolean>()
    val searchStage: LiveData<Boolean> get() = _searchStage

    init {
        _searchStage.value = false
    }

    fun changeSearchStage(newState: Boolean) {
        _searchStage.value = newState
    }
}
