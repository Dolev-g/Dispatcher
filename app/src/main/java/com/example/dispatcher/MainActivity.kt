package com.example.dispatcher

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dispatcher.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (BuildConfig.DEBUG) {
            Log.d("Environment", "Running in Development Mode")
        } else {
            Log.d("Environment", "Running in Production Mode")
        }

        // Log the current Firebase app's project ID
        val firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val firebaseProjectId = firebaseAnalytics.appInstanceId
        Log.d("Firebase", "Connected to Firebase project: $firebaseProjectId")

        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)

        // Set the initial fragment and tab image
        replaceFragment(HomeFragment())
        selectTab(R.id.tab_home)

        // Set up tab click listeners
        binding.tabHome.setOnClickListener {
            replaceFragment(HomeFragment())
            selectTab(R.id.tab_home)
        }

        binding.tabFavorites.setOnClickListener {
            replaceFragment(FavoritesFragment())
            selectTab(R.id.tab_favorites)
        }

        binding.tabProfile.setOnClickListener {
            replaceFragment(ProfileFragment())
            selectTab(R.id.tab_profile)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentDisplay, fragment)
        fragmentTransaction.commit()
    }

    private fun selectTab(selectedTabId: Int) {
        binding.tabHome.isSelected = selectedTabId == R.id.tab_home
        binding.tabFavorites.isSelected = selectedTabId == R.id.tab_favorites
        binding.tabProfile.isSelected = selectedTabId == R.id.tab_profile
    }

}
