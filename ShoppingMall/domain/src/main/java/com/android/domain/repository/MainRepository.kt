package com.android.domain.repository

import com.android.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getProductList(): Flow<List<Product>>
}