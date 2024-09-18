package com.example.dispatcher.data.auth

import com.example.dispatcher.presentation.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.example.dispatcher.domain.auth.AuthError
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.tasks.await

class FirebaseAuthManager : IAuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun isUserExist(): Boolean {
        return auth.currentUser != null
    }

    override suspend fun createAccount(email: String, password: String): AuthResult {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            AuthResult(success = true, error = null)
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is FirebaseAuthWeakPasswordException -> AuthError.WEAK_PASSWORD.message
                is FirebaseAuthInvalidCredentialsException -> AuthError.INVALID_EMAIL.message
                is FirebaseAuthUserCollisionException -> AuthError.EMAIL_ALREADY_EXISTS.message
                else -> e.localizedMessage ?: AuthError.UNKNOWN_ERROR.message
            }
            AuthResult(success = false, error = errorMessage)
        }
    }

    override suspend fun checkLogin(email: String, password: String): AuthResult {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            AuthResult(success = true, error = null)
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is FirebaseAuthInvalidCredentialsException -> AuthError.INVALID_CREDENTIALS.message
                is FirebaseAuthInvalidUserException -> AuthError.USER_NOT_FOUND.message
                else -> e.localizedMessage ?: AuthError.UNKNOWN_ERROR.message
            }
            AuthResult(success = false, error = errorMessage)
        }
    }
}