package com.example.dispatcher.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dispatcher.databinding.ActivityAuthBinding
import com.example.dispatcher.databinding.ActivityMainBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //FirebaseCrashlyticsManager.initialize(this)


    }
}