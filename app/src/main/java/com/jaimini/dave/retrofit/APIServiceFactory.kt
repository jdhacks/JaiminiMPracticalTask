package com.mvvm.kot.Kotlin_Retrofit.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jaimini.dave.retrofit.pojo.ResponseData
import com.mvvm.kot.Kotlin_Retrofit.Repository.APIURL.Companion.RATESLIST
import com.mvvm.kot.Kotlin_Retrofit.Repository.APIURL.Companion.murl
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

public class APIServiceFactory {
    private fun providesOkHttpClientBuilder(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        return httpClient.readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS).build()

    }

    fun providesWebService(): LiveData<ArrayList<ResponseData>> {
        val data = MutableLiveData<ArrayList<ResponseData>>()
        var webserviceResponseList: ArrayList<ResponseData>?

        val response = ""
        try {
            val retrofit = Retrofit.Builder()
                    .baseUrl(APIURL.BASE_URL)
                    //.addConverterFactory(ScalarsConverterFactory.create())

                .addConverterFactory(GsonConverterFactory.create())
                    .client(providesOkHttpClientBuilder())
                    .build()

            //Defining retrofit api service
            val service = retrofit.create(APIService::class.java)
            //  response = service.makeRequest().execute().body();
            service.makeRequest(murl).enqueue(object : Callback<ArrayList<ResponseData>> {
                override fun onResponse(call: Call<ArrayList<ResponseData>>, response: Response<ArrayList<ResponseData>>) {
                    Log.d("Repository", "Response::::" + response.toString())

                    Log.d("Repository", "Response::::" + response.body()!!)
                    //getRateForCurrency(response!!.body()!!.getRates()!!)
                    webserviceResponseList = response.body();
                    data.setValue(webserviceResponseList!!)
                }

                override fun onFailure(call: Call<ArrayList<ResponseData>>, t: Throwable) {
                    Log.e    ("JSON PARSING", "Failed:::"+t.message)

                    Log.d("Repository", "Failed:::")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return data

    }

     }