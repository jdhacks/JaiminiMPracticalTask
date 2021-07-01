package com.jaimini.dave.loginnewuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaimini.dave.R
import com.jaimini.dave.databinding.ActivityLoginBinding
import com.jaimini.dave.databinding.ActivityLoginNewBinding
import com.jaimini.dave.loginuser.ui.login.LoginViewModel

class LoginNew : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new)
    }
}