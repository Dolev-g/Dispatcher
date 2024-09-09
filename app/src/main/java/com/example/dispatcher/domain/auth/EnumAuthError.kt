package com.example.dispatcher.domain.auth

enum class AuthError(val message: String) {
    WEAK_PASSWORD("Password is too weak. Please choose a stronger password."),
    INVALID_EMAIL("Invalid email format. Please enter a valid email."),
    EMAIL_ALREADY_EXISTS("This email is already registered. Please use a different email."),
    INVALID_CREDENTIALS("Invalid credentials. Please try again."),
    USER_NOT_FOUND("User not found. Please sign up first."),
    UNKNOWN_ERROR("An unknown error occurred. Please try again.")
}