package com.android.domain.usecase

import com.android.domain.model.Product
import com.android.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor(
    private val repository: ProductDetailRepository
) {
    fun getDetailProduct(productId: String): Flow<Product> {
        return repository.getProductDetail(productId)
    }
}