package com.example.newsapplication.apicall

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "http://newsapi.org/v2/"

//Query to Call Data from Web Server
interface NewsAPIService {

    @GET("top-headlines?country=id")
    fun getPropertyNetwork(@Query("apiKey") apiKey : String, @Query("category") categori : String ):
            Deferred<News>




}

/*//Convert JSON to Kotlin Object/List
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()*/

//Build Connection with Web Server
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()


//Create Retrofit
object NewsAPI {
    val retrofitService: NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }
}

