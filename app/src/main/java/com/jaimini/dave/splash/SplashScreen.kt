package com.jaimini.dave.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.jaimini.dave.MainActivity
import com.jaimini.dave.databinding.ActivityHomeBinding
import com.jaimini.dave.databinding.ActivitySplashScreenBinding
import com.jaimini.dave.loginuser.ui.login.LoginActivity


class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed(Runnable {
            finish()
            startActivity( Intent(this@SplashScreen, LoginActivity::class.java))
        }, 2000)

    }
}