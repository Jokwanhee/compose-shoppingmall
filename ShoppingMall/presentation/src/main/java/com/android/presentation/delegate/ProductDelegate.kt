package com.android.presentation.delegate

import androidx.navigation.NavHostController
import com.android.domain.model.Product

interface ProductDelegate {
    fun openProduct(navHostController: NavHostController, product: Product)
}