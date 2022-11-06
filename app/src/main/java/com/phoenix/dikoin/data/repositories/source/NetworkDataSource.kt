package com.phoenix.dikoin.data.repositories.source

import com.phoenix.dikoin.data.dto.ApiResponse
import retrofit2.Response

interface NetworkDataSource {
    suspend fun getNewsFromApi( page:Int, country:String): Response<ApiResponse>
    suspend fun getSearchedNews(country : String,searchQuery:String, page : Int): Response<ApiResponse>
}