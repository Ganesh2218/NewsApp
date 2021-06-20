package com.example.newsapplication.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapplication.apicall.NewsProperty

@Entity
class DatabaseNews constructor(
    @PrimaryKey
    val url: String,
    val author: String?,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val category : String?
)

fun List<DatabaseNews>.asDomainModel(): List<NewsProperty> {
    return map {
        NewsProperty(
            url = it.url,
            author = it.author,
            title = it.title,
            description = it.description,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content,
            category = it.category
        )
    }
}

