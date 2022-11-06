package com.phoenix.dikoin.domain.repositories

import com.phoenix.dikoin.data.dto.ApiResponse
import com.phoenix.dikoin.data.dto.Article
import com.phoenix.dikoin.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getHeadLines(page:Int,country:String): Resource<ApiResponse>
    suspend fun getSearchedNews(country: String, searchQuery: String, page: Int): Resource<ApiResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}