package com.example.dispatcher.common.base

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.os.Handler
import com.example.dispatcher.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            checkUserStatus()
        }, 3000)
    }

    private fun checkUserStatus() {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this@SplashActivity, AuthActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}
