package com.example.newsapplication

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

class Constants{
    companion object{
        const val API_KEY = "fef3f46a581441f2ac13b2b2752ef1e3"

         fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        fun displayToast(
            context: Context?,
            message: String?
        ) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}
