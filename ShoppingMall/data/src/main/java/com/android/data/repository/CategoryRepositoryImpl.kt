package com.android.data.repository

import com.android.data.datasource.ProductDataSource
import com.android.domain.model.Category
import com.android.domain.model.Product
import com.android.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource
) : CategoryRepository {
    override fun getCategories(): Flow<List<Category>> = flow {
        emit(
            listOf(
                Category.Top,
                Category.Bag,
                Category.Dress,
                Category.Pants,
                Category.Outerwear,
                Category.FashionAccessories,
                Category.Shoes,
                Category.Skirt,
            )
        )
    }

    override fun getProductByCategory(category: Category): Flow<List<Product>>  {
        return dataSource.getProducts().map { list ->
            list.filterIsInstance<Product>()
                .filter { product ->
                    product.category.categoryId == category.categoryId
                }
        }
    }
}