package com.android.presentation.model

import androidx.navigation.NavHostController
import com.android.domain.model.Carousel
import com.android.domain.model.Product
import com.android.presentation.delegate.ProductDelegate

class CarouselVM(model: Carousel, private val productDelegate: ProductDelegate) :
    PresentationVM<Carousel>(model) {
    fun openCarouselProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController, product)
        sendCarouselLog()
    }

    private fun sendCarouselLog() {}
}