package com.example.dispatcher

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeTab: ImageView = findViewById(R.id.tab_home)
        val favoritesTab: ImageView = findViewById(R.id.tab_favorites)
        val profileTab: ImageView = findViewById(R.id.tab_profile)

        // Set the initial fragment and tab image
        replaceFragment(HomeFragment())
        updateTabImages(selectedTab = R.id.tab_home)

        homeTab.setOnClickListener {
            replaceFragment(HomeFragment())
            updateTabImages(selectedTab = R.id.tab_home)
        }

        favoritesTab.setOnClickListener {
            replaceFragment(FavoritesFragment())
            updateTabImages(selectedTab = R.id.tab_favorites)
        }

        profileTab.setOnClickListener {
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
        val homeTab: ImageView = findViewById(R.id.tab_home)
        val favoritesTab: ImageView = findViewById(R.id.tab_favorites)
        val profileTab: ImageView = findViewById(R.id.tab_profile)

        when (selectedTab) {
            R.id.tab_home -> {
                homeTab.setImageResource(R.drawable.home_clicked)
                favoritesTab.setImageResource(R.drawable.star)
                profileTab.setImageResource(R.drawable.profile)
            }
            R.id.tab_favorites -> {
                homeTab.setImageResource(R.drawable.home)
                favoritesTab.setImageResource(R.drawable.star_clicked)
                profileTab.setImageResource(R.drawable.profile)
            }
            R.id.tab_profile -> {
                homeTab.setImageResource(R.drawable.home)
                favoritesTab.setImageResource(R.drawable.star)
                profileTab.setImageResource(R.drawable.profile_clicked)
            }
        }
    }
}
