package com.example.dispatcher.presentation.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.dispatcher.R
import com.example.dispatcher.common.utils.FirebaseCrashlyticsManager
import com.example.dispatcher.common.utils.showView
import com.example.dispatcher.databinding.ActivityMainBinding
import com.example.dispatcher.presentation.favorites.view.FavoritesFragment
import com.example.dispatcher.presentation.filter.view.FilterWrapperFragment
import com.example.dispatcher.presentation.homepage.view.HomeFragment
import com.example.dispatcher.presentation.profile.view.ProfileFragment
import com.example.dispatcher.presentation.search.view.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout

        FirebaseCrashlyticsManager.initialize(this)

        setListeners()
        replaceFragment(HomeFragment())
        selectTab(R.id.tab_home)

        supportFragmentManager.beginTransaction()
            .replace(R.id.right_drawer, FilterWrapperFragment())
            .commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentDisplay, fragment)
        fragmentTransaction.commit()
    }

    private fun selectTab(selectedTabId: Int) {
        binding.apply {
            tabHome.isSelected = selectedTabId == R.id.tab_home
            tabFavorites.isSelected = selectedTabId == R.id.tab_favorites
            tabProfile.isSelected = selectedTabId == R.id.tab_profile
        }
    }

    private fun setListeners() {
        binding.apply {
            tabHome.setOnClickListener {
                replaceFragment(HomeFragment())
                selectTab(R.id.tab_home)
            }

            tabFavorites.setOnClickListener {
                replaceFragment(FavoritesFragment())
                selectTab(R.id.tab_favorites)
            }

            tabProfile.setOnClickListener {
                replaceFragment(ProfileFragment())
                selectTab(R.id.tab_profile)
            }

            customHeaderView.setSearchIconAction {
                replaceFragment(SearchFragment())
                binding.customHeaderView.showView(false)
                binding.tabsLayout.showView(false)
            }
        }
    }

    fun onBackClick() {
        replaceFragment(HomeFragment())
        binding.apply {
            customHeaderView.showView(true)
            tabsLayout.showView(true)
        }
    }

     fun onFilterClick() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    fun closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.END)
    }
}
