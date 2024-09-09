package com.example.dispatcher.presentation.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.data.auth.FirebaseAuthManager
import com.example.dispatcher.domain.auth.EnumNavigate

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val authManager = FirebaseAuthManager()

    private val stage = MutableLiveData<EnumNavigate>()
    private val authResult = MutableLiveData<AuthResult>()
    private val loader = MutableLiveData<Boolean>()

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
        authManager.createAccount(email, password) { result ->
            authResult.value = result
        }
    }

    fun checkLogin(email: String, password: String) {
        authManager.checkLogin(email, password) { result ->
            authResult.value = result
        }
    }
}
