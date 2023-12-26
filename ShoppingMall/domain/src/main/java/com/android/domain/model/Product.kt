package com.android.domain.model

data class Product(
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category,
    val salesStatus: SalesStatus,
    val shop: Shop,
    val isNew: Boolean,
    val isFreeShipping: Boolean
)
