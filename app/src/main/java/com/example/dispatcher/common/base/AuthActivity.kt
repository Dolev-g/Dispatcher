package com.example.dispatcher.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dispatcher.R
import com.example.dispatcher.databinding.ActivityAuthBinding
import com.example.dispatcher.databinding.ActivityMainBinding
import com.example.dispatcher.presentation.favorites.view.LoginFragment
import com.example.dispatcher.presentation.homepage.view.HomeFragment

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //FirebaseCrashlyticsManager.initialize(this)
        replaceFragment(LoginFragment())


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentDisplayAuth, fragment)
        fragmentTransaction.commit()
    }
}