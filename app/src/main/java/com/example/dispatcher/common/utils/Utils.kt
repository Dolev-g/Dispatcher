package com.example.dispatcher.common.utils

import android.content.Context
import android.util.Patterns
import android.widget.ImageView
import com.bumptech.glide.Glide

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

        fun loadImage(context: Context, imageUrl: String?, placeholder: Int, imageView: ImageView) {
            Glide.with(context)
                .load(imageUrl)
                .placeholder(placeholder)
                .into(imageView)
        }
    }
}

