package com.android.presentation.model

import com.android.domain.model.Carousel
import com.android.domain.model.Product
import com.android.presentation.delegate.ProductDelegate

class CarouselVM(model: Carousel, private val productDelegate: ProductDelegate) :
    PresentationVM<Carousel>(model),
    ProductDelegate by productDelegate {
    fun openCarouselProduct(product: Product) {
        productDelegate.openProduct(product)
        sendCarouselLog()
    }
    private fun sendCarouselLog() {}
}