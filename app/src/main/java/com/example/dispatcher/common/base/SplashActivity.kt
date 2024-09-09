package com.example.dispatcher.common.base

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.os.Handler
import com.example.dispatcher.R
import com.example.dispatcher.data.auth.FirebaseAuthManager
import com.example.dispatcher.presentation.auth.view.AuthActivity

class SplashActivity : ComponentActivity() {

    private val authManager = FirebaseAuthManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            checkUserStatus()
        }, 3000)
    }

    private fun checkUserStatus() {
        val targetActivity = if (authManager.getAuth().currentUser != null) {
            MainActivity::class.java
        } else {
            AuthActivity::class.java
        }

        val intent = Intent(this@SplashActivity, targetActivity)
        startActivity(intent)
        finish()
    }

}
