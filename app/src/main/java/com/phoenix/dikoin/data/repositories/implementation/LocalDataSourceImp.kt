package com.phoenix.dikoin.data.repositories.implementation

import com.phoenix.dikoin.data.dto.Article
import com.phoenix.dikoin.data.localdatabase.NewsDao
import com.phoenix.dikoin.data.repositories.source.LocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LocalDataSourceImp(private val dao: NewsDao) : LocalDataSource {
    override fun getNewsFromDataBase(): Flow<List<Article>> {
        return dao.getNews()
    }

    override suspend fun saveNewsToDataBase(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.saveNews(article)
        }
    }

    override suspend fun deleteNewsFromDatabase(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteNews(article)
        }
    }
}