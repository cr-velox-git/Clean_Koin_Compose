package com.phoenix.dikoin.data.remote


import com.phoenix.dikoin.BuildConfig
import com.phoenix.dikoin.data.dto.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {


    @GET("/v2/top-headlines")
    suspend fun getHeading(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apikey: String = BuildConfig.API_KEY
    ): retrofit2.Response<ApiResponse>


    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country")
        country:String,
        @Query("q")
        searchQuery:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY
    ): retrofit2.Response<ApiResponse>

}