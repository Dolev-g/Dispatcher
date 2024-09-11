package com.example.dispatcher.presentation.auth.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dispatcher.R
import com.example.dispatcher.presentation.main.view.MainActivity
import com.example.dispatcher.common.utils.showView
import com.example.dispatcher.databinding.ActivityAuthBinding
import com.example.dispatcher.domain.auth.EnumNavigate
import com.example.dispatcher.presentation.auth.AuthViewModel

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeObservers()
        subscribeToLoader()
    }

    private fun subscribeObservers() {
        authViewModel.getStage().observe(this) { currentStage ->
            when (currentStage) {
                EnumNavigate.LOGIN -> replaceFragment(LoginFragment())
                EnumNavigate.SIGNUP -> replaceFragment(SignupFragment())
                EnumNavigate.MAIN -> startActivity(Intent(this, MainActivity::class.java)).also { finish() }
            }
        }
    }

    private fun subscribeToLoader() {
        authViewModel.getLoader().observe(this) { isLoading ->
            binding.progressBarAuth.showView(isLoading)
            binding.dimOverlay.showView(isLoading)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentDisplayAuth, fragment)
        fragmentTransaction.commit()
    }
}