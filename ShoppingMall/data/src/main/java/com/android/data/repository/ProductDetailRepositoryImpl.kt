package com.android.data.repository

import com.android.data.datasource.ProductDataSource
import com.android.domain.model.Product
import com.android.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource
) : ProductDetailRepository {
    override fun getProductDetail(productId: String): Flow<Product> {
        return dataSource.getProducts().map { products ->
            products.filterIsInstance<Product>().first { it.productId == productId }
        }
    }
}