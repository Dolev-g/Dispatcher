package com.example.dispatcher.presentation.main.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.dispatcher.R
import com.example.dispatcher.common.utils.FirebaseCrashlyticsManager
import com.example.dispatcher.databinding.ActivityMainBinding
import com.example.dispatcher.presentation.favorites.view.FavoritesFragment
import com.example.dispatcher.presentation.homepage.view.HomeFragment
import com.example.dispatcher.presentation.main.MainViewModel
import com.example.dispatcher.presentation.profile.view.ProfileFragment
import com.example.dispatcher.presentation.search.view.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout

        FirebaseCrashlyticsManager.initialize(this)

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

        subscribeToSearchStage()
        subscribeToFilterDrawerDisplay()
        binding.customHeaderView.setViewModel(mainViewModel)

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

    private fun subscribeToSearchStage() {
        mainViewModel.searchStage.observe(this) { stage ->

            if (stage) {
                replaceFragment(SearchFragment())
                binding.customHeaderView.visibility = View.GONE
                binding.tabsLayout.visibility = View.GONE

            } else {
                replaceFragment(HomeFragment())
                binding.customHeaderView.visibility = View.VISIBLE
                binding.tabsLayout.visibility = View.VISIBLE

            }
        }
    }

    private fun subscribeToFilterDrawerDisplay() {
        mainViewModel.filterDrawerDisplay.observe(this) { stage ->

            if (stage) {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END)
                } else {
                    drawerLayout.openDrawer(GravityCompat.END)
                }
            }
        }
    }
}
