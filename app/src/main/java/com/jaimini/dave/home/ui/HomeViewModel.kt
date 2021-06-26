package com.jaimini.dave.home.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jaimini.dave.retrofit.pojo.ResponseData
import com.mvvm.kot.Kotlin_Retrofit.Repository.APIServiceFactory
import com.mvvm.kot.Kotlin_Retrofit.Repository.APIURL

class HomeViewModel : AndroidViewModel {
    private var retroRepository: APIServiceFactory
    private val retroObservable: LiveData<ArrayList<ResponseData>>

    constructor(application: Application): super(application){
        retroRepository = APIServiceFactory()
        //retroObservable = null
        retroObservable = retroRepository.providesWebService()
        // ratenamevalue= RateNameVal("AED",retroObservable!!.value!!.getRates()!!.getAed())
        Log.e("TAG","data"+retroObservable.value)
        //   retroRepository.providesWebServiceStr()
    }

    fun getProjectRetroListObservable(): LiveData<ArrayList<ResponseData>> {
        return retroObservable
    }


}