package com.android.domain.usecase

import com.android.domain.model.Category
import com.android.domain.model.Product
import com.android.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    fun getCategories(): Flow<List<Category>> {
        return repository.getCategories()
    }
    fun getProductsByCategory(category: Category): Flow<List<Product>> {
        return repository.getProductByCategory(category)
    }
}