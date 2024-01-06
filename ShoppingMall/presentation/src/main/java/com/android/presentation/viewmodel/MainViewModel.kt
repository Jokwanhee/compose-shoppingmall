package com.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.android.domain.model.Banner
import com.android.domain.model.BannerList
import com.android.domain.model.BaseModel
import com.android.domain.model.Carousel
import com.android.domain.model.Category
import com.android.domain.model.ModelType
import com.android.domain.model.Product
import com.android.domain.model.Ranking
import com.android.domain.usecase.CategoryUseCase
import com.android.domain.usecase.MainUseCase
import com.android.presentation.delegate.BannerDelegate
import com.android.presentation.delegate.CategoryDelegate
import com.android.presentation.delegate.ProductDelegate
import com.android.presentation.model.BannerListVM
import com.android.presentation.model.BannerVM
import com.android.presentation.model.CarouselVM
import com.android.presentation.model.PresentationVM
import com.android.presentation.model.ProductVM
import com.android.presentation.model.RankingVM
import com.android.presentation.ui.NavigationRouteName
import com.android.presentation.utils.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainUseCase: MainUseCase,
    categoryUseCase: CategoryUseCase
) : ViewModel(), ProductDelegate, BannerDelegate, CategoryDelegate {
    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount: StateFlow<Int> = _columnCount.asStateFlow()

    val modelList = mainUseCase.getModelList().map(::convertToPresentationVM)
    val categoryList = categoryUseCase.getCategories()

    fun openSearchForm() {
        println("openSearchForm")
    }

    fun updateColumnCount(count: Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    override fun openProduct(navHostController: NavHostController, product: Product) {
        NavigationUtils.navigate(navHostController, NavigationRouteName.PRODUCT_DETAIL, product)
    }

    override fun openBanner(bannerId: String) {
    }

    override fun openCategory(navHostController: NavHostController, category: Category) {
        NavigationUtils.navigate(navHostController, NavigationRouteName.CATEGORY, category)
    }

    private fun convertToPresentationVM(list: List<BaseModel>): List<PresentationVM<out BaseModel>> {
        return list.map { model ->
            when (model) {
                is Product -> ProductVM(model, this)
                is Ranking -> RankingVM(model, this)
                is Carousel -> CarouselVM(model, this)
                is Banner -> BannerVM(model, this)
                is BannerList -> BannerListVM(model, this)
            }
        }
    }

    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }
}