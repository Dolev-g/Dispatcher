package com.example.dispatcher.common.base

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.dispatcher.R
import com.example.dispatcher.databinding.ActivityAuthBinding
import com.example.dispatcher.presentation.favorites.viewModel.AuthViewModel
import com.example.dispatcher.presentation.login.view.LoginFragment
import com.example.dispatcher.presentation.login.view.SignupFragment

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(LoginFragment())
        subscribeObservers()
        subscribeToLoader()
    }

    private fun subscribeObservers() {
        authViewModel.getStage().observe(this, Observer { currentStage ->
            when (currentStage) {
                "login" -> replaceFragment(LoginFragment())
                "signup" -> replaceFragment(SignupFragment())
            }
        })
    }

    private fun subscribeToLoader() {
        authViewModel.getLoader().observe(this, Observer { isLoading ->
            if (isLoading == true) {
                binding.progressBarAuth.visibility = View.VISIBLE
            } else {
                binding.progressBarAuth.visibility = View.GONE
            }
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentDisplayAuth, fragment)
        fragmentTransaction.commit()
    }
}