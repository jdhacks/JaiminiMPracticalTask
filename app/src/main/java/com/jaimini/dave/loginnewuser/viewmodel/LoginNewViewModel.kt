package com.jaimini.dave.loginnewuser.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaimini.dave.R
import com.jaimini.dave.loginnewuser.data.LoginNewRepo
import com.jaimini.dave.loginuser.ui.login.LoginFormState

class LoginNewViewModel : AndroidViewModel {

    private var validationRepository: LoginNewRepo

    constructor(application: Application) : super(application){
        validationRepository = LoginNewRepo(application)
    }

    fun validateCredentials(email:String,passWord:String): LiveData<Int> {
           return validationRepository.validateCredentials(email, passWord)
     }


}