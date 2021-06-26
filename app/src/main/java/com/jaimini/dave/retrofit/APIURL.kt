package com.mvvm.kot.Kotlin_Retrofit.Repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jaimini.dave.retrofit.pojo.ResponseData

public class APIURL {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        lateinit var RATESLIST : ArrayList<ResponseData>
        var murl : String ="https://jsonplaceholder.typicode.com/photos"
     /*   public  fun checkNetworkConnection(context: Context): Boolean {
            var connected = false
            val connectivityManager : ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            try {
                connected =
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
                    ) {
                        //we are connected to a network
                        true
                    } else false
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return connected
        }
*/
    }
   // val BASE_URL = "https://jsonplaceholder.typicode.com/"
}