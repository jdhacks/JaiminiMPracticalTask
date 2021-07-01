package com.jaimini.dave.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.jaimini.dave.databinding.ActivitySplashScreenBinding
import com.jaimini.dave.home.ui.HomeActivity
import com.jaimini.dave.loginuser.ui.login.LoginActivity


class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var mintent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPref: SharedPreferences =
           getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        if(!sharedPref.getBoolean("signin", false))
        {
            mintent=Intent(this@SplashScreen, LoginActivity::class.java)
        }else{
            mintent= Intent(this@SplashScreen, HomeActivity::class.java)
        }

        Handler().postDelayed(Runnable {
            finish()
            startActivity(mintent)
        }, 2000)

    }
}