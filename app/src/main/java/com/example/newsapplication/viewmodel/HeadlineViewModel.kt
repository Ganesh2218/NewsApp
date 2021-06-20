package com.example.newsapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.newsapplication.Constants
import com.example.newsapplication.R
import com.example.newsapplication.apicall.NewsProperty
import com.example.newsapplication.localdb.getDatabase
import com.example.newsapplication.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HeadlineViewModel(application: Application) : AndroidViewModel(application) {

    private val detailNews = MutableLiveData<NewsProperty>()
    val navigateToDetailNews: LiveData<NewsProperty> get() = detailNews

    private val viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    private val database = getDatabase(application)
    private val newsRepository = NewsRepository(database)

    init {
        coroutineScope.launch {
            if(Constants.isNetworkAvailable(application)) {
                newsRepository.insertToLocalDb("general")
            }else{
                Constants.displayToast(application, application.getString(R.string.no_internet))
            }
        }
    }

    val listNewsGeneral = newsRepository.newsLiveData


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onItemSelected(newsProperty: NewsProperty) {
        detailNews.value = newsProperty
    }

    fun onFinishItemSelected() {
        detailNews.value = null
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HeadlineViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HeadlineViewModel(app) as T
            }
            throw IllegalArgumentException("Failed")
        }
    }
}