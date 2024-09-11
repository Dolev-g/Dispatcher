package com.example.dispatcher.data.auth

import com.example.dispatcher.domain.auth.AuthError
import com.example.dispatcher.presentation.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FirebaseAuthManager : IAuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun isUserExist(): Boolean {
        return auth.currentUser != null
    }

    override fun createAccount(email: String, password: String, callback: (AuthResult) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(AuthResult(success = true, error = null))
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthWeakPasswordException -> AuthError.WEAK_PASSWORD.message
                        is FirebaseAuthInvalidCredentialsException -> AuthError.INVALID_EMAIL.message
                        is FirebaseAuthUserCollisionException -> AuthError.EMAIL_ALREADY_EXISTS.message
                        else -> task.exception?.localizedMessage ?: AuthError.UNKNOWN_ERROR.message
                    }
                    callback(AuthResult(success = false, error = errorMessage))
                }
            }
    }

    override fun checkLogin(email: String, password: String, callback: (AuthResult) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(AuthResult(success = true, error = null))
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthInvalidCredentialsException -> AuthError.INVALID_CREDENTIALS.message
                        is FirebaseAuthInvalidUserException -> AuthError.USER_NOT_FOUND.message
                        else -> task.exception?.localizedMessage ?: AuthError.UNKNOWN_ERROR.message
                    }
                    callback(AuthResult(success = false, error = errorMessage))
                }
            }
    }
}
