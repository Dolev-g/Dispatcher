package com.example.dispatcher.data.auth

import com.example.dispatcher.presentation.auth.AuthResult

interface IAuthManager {
    fun isUserExist(): Boolean
    suspend fun createAccount(email: String, password: String): AuthResult
    suspend fun checkLogin(email: String, password: String): AuthResult
}