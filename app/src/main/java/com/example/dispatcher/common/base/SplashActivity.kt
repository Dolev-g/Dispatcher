package com.example.dispatcher.common.base

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

            val intent = Intent(this@SplashActivity, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
