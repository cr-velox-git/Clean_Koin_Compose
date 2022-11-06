package com.phoenix.dikoin.data.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.phoenix.dikoin.data.dto.Article
import com.phoenix.dikoin.data.localdatabase.Converters
import com.phoenix.dikoin.data.localdatabase.NewsDao

@Database(entities = [Article::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}