package com.example.dispatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.content.Intent
import android.os.Handler
import com.example.dispatcher.R

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // Start MainActivity
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the SplashActivity
        }, 3000)
    }
}
