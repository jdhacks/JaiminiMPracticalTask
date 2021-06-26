package com.mvvm.kot.Kotlin_Retrofit.Repository

import com.jaimini.dave.retrofit.pojo.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

public interface APIService {
    @GET
    abstract fun makeRequest(@Url id :String): Call<ArrayList<ResponseData>>


}