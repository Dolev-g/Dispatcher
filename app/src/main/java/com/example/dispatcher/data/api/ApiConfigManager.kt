package com.example.dispatcher.data.api

import com.ibm.icu.util.ULocale

object ApiConfigManager {
    const val BASE_URL = "https://newsapi.org/v2/"
    const val TOP_HEADLINES_ENDPOINT = "top-headlines"
    private var countryCode = "us"

    fun getCountryCode(): String {
        return countryCode
    }

    fun updateCountryCode(newCountryCode: String) {
        countryCode = newCountryCode
    }

    fun isValidCountryCode(code: String): Boolean {
        val locale = ULocale("", code.toUpperCase())
        return locale.country.isNotEmpty()
    }
}
