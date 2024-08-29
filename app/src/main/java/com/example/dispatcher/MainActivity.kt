package com.example.dispatcher

import android.os.Bundle
import android.util.Log
import android.view.View
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
        updateTabImages(selectedTab = R.id.tab_home)

        // Set up tab click listeners
        binding.tabHome.setOnClickListener {
            replaceFragment(HomeFragment())
            updateTabImages(selectedTab = R.id.tab_home)
        }

        binding.tabFavorites.setOnClickListener {
            replaceFragment(FavoritesFragment())
            updateTabImages(selectedTab = R.id.tab_favorites)
        }

        binding.tabProfile.setOnClickListener {
            replaceFragment(ProfileFragment())
            updateTabImages(selectedTab = R.id.tab_profile)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentDisplay, fragment)
        fragmentTransaction.commit()
    }

    private fun updateTabImages(selectedTab: Int) {
        when (selectedTab) {
            R.id.tab_home -> {
                binding.tabHome.setImageResource(R.drawable.home_clicked)
                binding.tabFavorites.setImageResource(R.drawable.star)
                binding.tabProfile.setImageResource(R.drawable.profile)
            }
            R.id.tab_favorites -> {
                binding.tabHome.setImageResource(R.drawable.home)
                binding.tabFavorites.setImageResource(R.drawable.star_clicked)
                binding.tabProfile.setImageResource(R.drawable.profile)
            }
            R.id.tab_profile -> {
                binding.tabHome.setImageResource(R.drawable.home)
                binding.tabFavorites.setImageResource(R.drawable.star)
                binding.tabProfile.setImageResource(R.drawable.profile_clicked)
            }
        }
    }

    fun forceCrash(view: View) {
        throw RuntimeException("This is a test crash") // Force a crash
    }
}
