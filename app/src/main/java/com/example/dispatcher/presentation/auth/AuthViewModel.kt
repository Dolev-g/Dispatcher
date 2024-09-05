package com.example.dispatcher.presentation.favorites.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val stage = MutableLiveData<String>()
    private val _authResult = MutableLiveData<Boolean>()
    val authResult: LiveData<Boolean> get() = _authResult

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        stage.value = "login"
    }

    fun changeStage(newStage: String) {
        stage.value = newStage
    }

    fun getStage(): LiveData<String> {
        return stage
    }

    fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authResult.value = true
                } else {
                    _authResult.value = false
                }
            }
    }

    fun checkLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authResult.value = true
                } else {
                    _authResult.value = false
                }
            }
    }
}
