package com.example.dispatcher.presentation.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dispatcher.data.auth.FirebaseAuthManager
import com.example.dispatcher.domain.auth.EnumNavigate
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val authManager = FirebaseAuthManager()

    private val stage = MutableLiveData<EnumNavigate>()
    private val authResult = MutableLiveData<AuthResult>()
    private val loader = MutableLiveData<Boolean>()
    private var authJob: Job? = null

    init {
        stage.value = EnumNavigate.LOGIN
        loader.value = false
    }

    fun changeStage(newStage: EnumNavigate) {
        stage.value = newStage
    }

    fun getStage(): LiveData<EnumNavigate> {
        return stage
    }

    fun getLoader(): LiveData<Boolean> {
        return loader
    }

    fun getAuthResult () : LiveData<AuthResult> {
        return authResult
    }

    fun changeLoader(newState: Boolean) {
        loader.value = newState
    }

    fun createAccount(email: String, password: String) {
        authJob?.cancel()
        authJob = viewModelScope.launch {
            try {
                loader.value = true
                val result = authManager.createAccount(email, password)
                authResult.value = result
            } catch (e: Exception) {
                authResult.value = AuthResult(success = false, error = e.localizedMessage)
            } finally {
                loader.value = false
            }
        }
    }

    fun checkLogin(email: String, password: String) {
        authJob?.cancel()
        authJob = viewModelScope.launch {
            try {
                loader.value = true
                val result = authManager.checkLogin(email, password)
                authResult.value = result
            } catch (e: Exception) {
                authResult.value = AuthResult(success = false, error = e.localizedMessage)
            } finally {
                loader.value = false
            }
        }
    }
}
