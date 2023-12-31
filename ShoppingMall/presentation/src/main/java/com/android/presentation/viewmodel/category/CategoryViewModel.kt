package com.android.presentation.viewmodel.category

import androidx.lifecycle.ViewModel
import com.android.domain.model.Banner
import com.android.domain.model.BannerList
import com.android.domain.model.Carousel
import com.android.domain.model.Category
import com.android.domain.model.Product
import com.android.domain.model.Ranking
import com.android.domain.usecase.CategoryUseCase
import com.android.presentation.delegate.ProductDelegate
import com.android.presentation.model.BannerListVM
import com.android.presentation.model.BannerVM
import com.android.presentation.model.CarouselVM
import com.android.presentation.model.ProductVM
import com.android.presentation.model.RankingVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: CategoryUseCase
): ViewModel(), ProductDelegate {
    private val _products = MutableStateFlow<List<ProductVM>>(listOf())
    val products: StateFlow<List<ProductVM>> = _products.asStateFlow()

    suspend fun updateCategory(category: Category) {
        useCase.getProductsByCategory(category).collectLatest {
            _products.emit(convertToPresentationVM(it))
        }
    }

    override fun openProduct(product: Product) {

    }

    private fun convertToPresentationVM(list: List<Product>): List<ProductVM> {
        return list.map {
            ProductVM(it, this)
        }
    }
}