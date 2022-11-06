package com.phoenix.dikoin.data.repositories

import com.phoenix.dikoin.data.dto.ApiResponse
import com.phoenix.dikoin.data.dto.Article
import com.phoenix.dikoin.data.repositories.source.LocalDataSource
import com.phoenix.dikoin.data.repositories.source.NetworkDataSource
import com.phoenix.dikoin.domain.repositories.MainRepository
import com.phoenix.dikoin.utils.Resource
import kotlinx.coroutines.flow.Flow


class MainRepositoryImp(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : MainRepository {
    override suspend fun getHeadLines(page: Int, country: String): Resource<ApiResponse> {
        val response = networkDataSource.getNewsFromApi(page, country)
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<ApiResponse> {
        val response = networkDataSource.getSearchedNews(country, searchQuery, page)
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveNews(article: Article) {
        localDataSource.saveNewsToDataBase(article)
    }


    override suspend fun deleteNews(article: Article) {
        localDataSource.deleteNewsFromDatabase(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return localDataSource.getNewsFromDataBase()
    }

//    override fun getSavedNews(): Flow<List<Article>> {
//        //for room db
//        return Flow<List<Article>>
//    }

}