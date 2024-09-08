package com.example.dispatcher.presentation.favorites.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.presentation.auth.AuthManager
import com.example.dispatcher.presentation.auth.AuthResult

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val authManager = AuthManager()

    private val stage = MutableLiveData<String>()
    private val _authResult = MutableLiveData<AuthResult>()
    val authResult: LiveData<AuthResult> get() = _authResult
    private val loader = MutableLiveData<Boolean>().apply { value = false }

    init {
        stage.value = "login"
    }

    fun changeStage(newStage: String) {
        stage.value = newStage
    }

    fun getStage(): LiveData<String> {
        return stage
    }

    fun getLoader(): LiveData<Boolean> {
        return loader
    }

    fun changeLoader(newState: Boolean) {
        loader.value = newState
    }

    fun createAccount(email: String, password: String) {
        authManager.createAccount(email, password) { result ->
            _authResult.value = result
        }
    }

    fun checkLogin(email: String, password: String) {
        authManager.checkLogin(email, password) { result ->
            _authResult.value = result
        }
    }
}
