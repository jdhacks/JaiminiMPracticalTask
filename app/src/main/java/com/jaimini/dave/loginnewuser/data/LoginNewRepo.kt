package com.jaimini.dave.loginnewuser.data

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginNewRepo  (application: Application) {

    var application:Application

    init {
        this.application = application
    }
    fun validateCredentials(emailID:String,password:String): LiveData<Int> {
        val loginstaus = MutableLiveData<Int>()

        val loginErrorMessage = MutableLiveData<String>()
        if (isEmailValid(emailID)) {
            if (!isPasswordValid(password)) {
                loginstaus.value = 0;
                loginErrorMessage.value = "Invalid Password"
            } else {
                loginstaus.value = 2;
                loginErrorMessage.value = "Successful Login"
            }
        } else {
            loginstaus.value = 1;
            loginErrorMessage.value = "Invalid Email"
        }

        return loginstaus

    }
    private fun isEmailValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }


}