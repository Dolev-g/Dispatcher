package com.example.dispatcher.presentation.favorites.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dispatcher.presentation.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val stage = MutableLiveData<String>()

    private val _authResult = MutableLiveData<AuthResult>()
    val authResult: LiveData<AuthResult> get() = _authResult

    private val loader = MutableLiveData<Boolean>().apply { value = false }
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

    fun getLoader(): LiveData<Boolean> {
        return loader
    }

    fun changeLoader(newState: Boolean) {
        loader.value = newState
    }

    fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authResult.value = AuthResult(success = true, error = null)
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthWeakPasswordException -> "Password is too weak. Please choose a stronger password."
                        is FirebaseAuthInvalidCredentialsException -> "Invalid email format. Please enter a valid email."
                        is FirebaseAuthUserCollisionException -> "This email is already registered. Please use a different email."
                        else -> task.exception?.localizedMessage ?: "Signup failed. Please try again."
                    }
                    _authResult.value = AuthResult(success = false, error = errorMessage)
                }
            }
    }

    fun checkLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authResult.value = AuthResult(success = true, error = null)
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthInvalidCredentialsException -> "Invalid credentials. Please try again."
                        is FirebaseAuthInvalidUserException -> "User not found. Please sign up first."
                        else -> task.exception?.localizedMessage ?: "Login failed. Please try again."
                    }
                    _authResult.value = AuthResult(success = false, error = errorMessage)
                }
            }
    }

}
