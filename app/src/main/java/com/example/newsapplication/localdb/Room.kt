package com.example.newsapplication.localdb

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NewsDao {
    @Query("SELECT * FROM databasenews")
    fun getAllNews(): LiveData<List<DatabaseNews>>

    @Query("SELECT * FROM databasenews WHERE category = :category")
    fun getCategory(category: String) : LiveData<List<DatabaseNews>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg news: DatabaseNews)

    @Query("DELETE FROM databasenews")
    fun deleteAll()


}

@Database(entities = [DatabaseNews::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}

private lateinit var INSTANCE: NewsDatabase

fun getDatabase(context: Context): NewsDatabase {
    synchronized(NewsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news_db"
            ).build()
        }
    }
    return INSTANCE
}