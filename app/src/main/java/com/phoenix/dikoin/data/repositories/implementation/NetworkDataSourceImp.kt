package com.phoenix.dikoin.data.repositories.implementation

import com.phoenix.dikoin.data.remote.MyApi
import com.phoenix.dikoin.data.dto.ApiResponse
import com.phoenix.dikoin.data.repositories.source.NetworkDataSource
import retrofit2.Response

class NetworkDataSourceImp(
    private val myApi: MyApi
): NetworkDataSource {


    override suspend fun getNewsFromApi(page: Int, country: String): Response<ApiResponse> {
        return myApi.getHeading(country,page)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int,
    ): Response<ApiResponse> {
        return myApi.getSearchedTopHeadlines(country, searchQuery, page)
    }
}