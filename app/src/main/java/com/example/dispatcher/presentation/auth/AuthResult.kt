package com.example.dispatcher.presentation.auth

class AuthResult(
    val success: Boolean,  // Boolean to indicate success or failure
    val error: String?     // Nullable string to hold the error message (null if no error)
)
