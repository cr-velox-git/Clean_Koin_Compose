package com.phoenix.dikoin.data.repositories.source

import com.phoenix.dikoin.data.dto.Article
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getNewsFromDataBase(): Flow<List<Article>>
    suspend fun saveNewsToDataBase(article: Article)
    suspend fun deleteNewsFromDatabase(article: Article)

}