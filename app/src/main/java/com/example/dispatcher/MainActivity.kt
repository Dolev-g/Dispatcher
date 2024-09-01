package com.example.dispatcher

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dispatcher.databinding.ActivityMainBinding
import com.google.firebase.crashlytics.BuildConfig

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Environment", "BuildConfig.DEBUG: ${BuildConfig.DEBUG}")
        Log.d("Environment", "BuildConfig.BUILD_TYPE: ${BuildConfig.BUILD_TYPE}")

        if (BuildConfig.DEBUG) {
            Log.d("Environment", "Running in Development Mode")
        } else {
            Log.d("Environment", "Running in Production Mode")
        }

        // Initialize Firebase Crashlytics Manager
        FirebaseCrashlyticsManager.initialize(this)

        // Set the initial fragment and tab image
        replaceFragment(HomeFragment())
        selectTab(R.id.tab_home)

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
