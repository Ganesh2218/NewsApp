package com.example.newsapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.newsapplication.Constants
import com.example.newsapplication.apicall.NewsAPI
import com.example.newsapplication.apicall.NewsProperty
import com.example.newsapplication.apicall.asDatabaseModel
import com.example.newsapplication.localdb.NewsDatabase
import com.example.newsapplication.localdb.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val database: NewsDatabase) {

    val newsLiveData : LiveData<List<NewsProperty>> = Transformations.map(
        database.newsDao.getCategory("general")
    ){
        it.asDomainModel()
    }




    suspend fun insertToLocalDb(category : String) {
        withContext(Dispatchers.IO) {

            val dataListNews = NewsAPI.retrofitService.getNewsDataAsync(Constants.API_KEY, category).await()
            for (item in dataListNews.articles) {
               item.category = "general"
                database.newsDao.insertAll(item.asDatabaseModel())
            }
        }
    }
}