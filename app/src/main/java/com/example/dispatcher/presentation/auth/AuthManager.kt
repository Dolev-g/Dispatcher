package com.example.dispatcher.presentation.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class AuthManager(private val auth: FirebaseAuth = FirebaseAuth.getInstance()) {

    fun createAccount(email: String, password: String, callback: (AuthResult) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(AuthResult(success = true, error = null))
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthWeakPasswordException -> "Password is too weak. Please choose a stronger password."
                        is FirebaseAuthInvalidCredentialsException -> "Invalid email format. Please enter a valid email."
                        is FirebaseAuthUserCollisionException -> "This email is already registered. Please use a different email."
                        else -> task.exception?.localizedMessage ?: "Signup failed. Please try again."
                    }
                    callback(AuthResult(success = false, error = errorMessage))
                }
            }
    }

    fun checkLogin(email: String, password: String, callback: (AuthResult) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(AuthResult(success = true, error = null))
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthInvalidCredentialsException -> "Invalid credentials. Please try again."
                        is FirebaseAuthInvalidUserException -> "User not found. Please sign up first."
                        else -> task.exception?.localizedMessage ?: "Login failed. Please try again."
                    }
                    callback(AuthResult(success = false, error = errorMessage))
                }
            }
    }
}
