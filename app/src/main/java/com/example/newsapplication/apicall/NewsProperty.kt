package com.example.newsapplication.apicall

import android.os.Parcelable
import com.example.newsapplication.localdb.DatabaseNews
import kotlinx.android.parcel.Parcelize


data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsProperty>
)

@Parcelize
data class NewsProperty(
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    var category : String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
) : Parcelable

fun NewsProperty.asDatabaseModel(): DatabaseNews {
    return DatabaseNews(
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
        category = this.category
    )
}
