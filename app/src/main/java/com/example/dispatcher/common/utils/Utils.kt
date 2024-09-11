package com.example.dispatcher.common.utils

import android.util.Patterns

class Utils {
    companion object {
        fun isValidEmail(email: String): Boolean {
            val emailPattern = Patterns.EMAIL_ADDRESS
            return emailPattern.matcher(email).matches()
        }

        fun isValidPassword(password: String): Boolean {
            val passwordPattern = Regex("^(?=.*[0-9])(?=.*[a-zA-Z]).{7,}$")
            return password.matches(passwordPattern)
        }
    }
}
