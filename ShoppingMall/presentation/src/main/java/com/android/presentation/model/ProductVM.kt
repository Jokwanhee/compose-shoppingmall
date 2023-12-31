package com.android.presentation.model

import com.android.domain.model.Product
import com.android.presentation.delegate.ProductDelegate

class ProductVM(model: Product, productDelegate: ProductDelegate) :
    PresentationVM<Product>(model),
    ProductDelegate by productDelegate