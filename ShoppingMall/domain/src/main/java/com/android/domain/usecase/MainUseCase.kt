package com.android.domain.usecase

import android.util.Log
import com.android.domain.model.Product
import com.android.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun getProductList(): Flow<List<Product>> {
        return mainRepository.getProductList()
    }
}