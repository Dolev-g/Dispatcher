package com.example.dispatcher.data.auth

import com.example.dispatcher.presentation.auth.AuthResult

interface IAuthManager {
    fun isUserExist(): Boolean
    fun createAccount(email: String, password: String, callback: (AuthResult) -> Unit)
    fun checkLogin(email: String, password: String, callback: (AuthResult) -> Unit)
}