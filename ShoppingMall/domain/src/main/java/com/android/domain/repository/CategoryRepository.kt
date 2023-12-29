package com.android.domain.repository

import com.android.domain.model.Category
import com.android.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    fun getProductByCategory(category: Category): Flow<List<Product>>
}