package com.phoenix.dikoin.domain.usecase

import com.phoenix.dikoin.data.dto.ApiResponse
import com.phoenix.dikoin.domain.repositories.MainRepository
import com.phoenix.dikoin.utils.Resource

class GetHeadLineUseCase(private val repo: MainRepository) {
    suspend fun invoke(page: Int, country: String): Resource<ApiResponse> =
        repo.getHeadLines(page, country)
}