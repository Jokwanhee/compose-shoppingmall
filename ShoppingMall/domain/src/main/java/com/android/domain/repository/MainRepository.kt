package com.android.domain.repository

import com.android.domain.model.Product

interface MainRepository {
    fun getProductList(): List<Product>
}