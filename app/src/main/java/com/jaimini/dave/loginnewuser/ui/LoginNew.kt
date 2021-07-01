package com.jaimini.dave.loginnewuser.ui

import android.R.attr.value
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jaimini.dave.databinding.ActivityLoginNewBinding
import com.jaimini.dave.home.ui.HomeActivity
import com.jaimini.dave.loginnewuser.viewmodel.LoginNewViewModel
import com.jaimini.dave.loginuser.ui.login.afterTextChanged


class LoginNew : AppCompatActivity() {

    private lateinit var loginViewModel: LoginNewViewModel
    private lateinit var binding: ActivityLoginNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading


        loginViewModel = ViewModelProviders.of(this@LoginNew).get(LoginNewViewModel::class.java)
        setContentView(binding.root)

        username.afterTextChanged {
            loginViewModel.validateCredentials(
                username.text.toString(),
                password.text.toString()
            ).observe(this@LoginNew, object :
                Observer<Int> {
                override fun onChanged(t: Int?) {
                    if (t == 2) {
                        login.isEnabled=true
                    }else{
                             username.error = "Invalid Email Id"
                        }
                }
            })
        }

        password.apply {
            afterTextChanged {
                loginViewModel.validateCredentials(
                    username.text.toString(),
                    password.text.toString()
                ).observe(this@LoginNew, object :
                    Observer<Int> {
                    override fun onChanged(t: Int?) {
                        if (t == 2) {
                            login.isEnabled=true
                        }else{
                            password.error = "Invalid Password"
                        }
                    }
                })
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.validateCredentials(
                            username.text.toString(),
                            password.text.toString()
                        ).observe(this@LoginNew, object :
                            Observer<Int> {
                            override fun onChanged(t: Int?) {
                                if (t == 2) {
                                    login.isEnabled=true
                                }else{
                                    username.error = "Invalid Email Id"
                                    password.error = "Invalid Password"
                                }
                            }
                        })
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE

                loginViewModel.validateCredentials(username.text.toString(), password.text.toString()).observe(this@LoginNew, object :
                    Observer<Int> {
                    override fun onChanged(t: Int?) {

                        if (t != 1) {
                            if (t == 0) {
                                Toast.makeText(this@LoginNew, "Invalid Password", Toast.LENGTH_LONG)
                                    .show()


                            } else {

                                if (username.text.toString()
                                        .equals("hello@yogmail.com") && password.text.toString()
                                        .equals("Password@123")
                                ) {
                                    Toast.makeText(
                                        this@LoginNew,
                                        "Successful Login",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    val sharedPref = context.getSharedPreferences(
                                        "userPrefs",
                                        Context.MODE_PRIVATE
                                    )
                                    val editor = sharedPref.edit()
                                    editor.putBoolean("signin", true)
                                    editor.apply()

                                    startActivity(
                                        Intent(
                                            this@LoginNew,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@LoginNew,
                                        "Invalid Username or Password",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }
                            }
                        } else {
                            Toast.makeText(this@LoginNew, "Invalid Email", Toast.LENGTH_LONG)
                                .show()

                        }
                    }
                })

            }

        }
    }
}