package com.phoenix.dikoin.data.localdatabase

import androidx.room.*
import com.phoenix.dikoin.data.dto.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(article: Article)

    @Delete
    suspend fun deleteNews(article: Article)

    @Query("SELECT * FROM NEWSTABLE")
    fun getNews(): Flow<List<Article>>

}