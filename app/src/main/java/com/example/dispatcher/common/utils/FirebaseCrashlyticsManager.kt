package com.example.dispatcher

import android.content.Context
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

object FirebaseCrashlyticsManager {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var firebaseCrashlytics: FirebaseCrashlytics

    fun initialize(context: Context) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
        firebaseCrashlytics = FirebaseCrashlytics.getInstance()
        firebaseCrashlytics.setCrashlyticsCollectionEnabled(true)

        // Log the current Firebase app's project ID
        val firebaseProjectId = firebaseAnalytics.appInstanceId
        Log.d("Firebase", "Connected to Firebase project: $firebaseProjectId")
    }
}
