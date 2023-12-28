package com.android.presentation.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.android.domain.model.Banner
import com.android.domain.model.BannerList
import com.android.domain.model.Carousel
import com.android.domain.model.ModelType
import com.android.domain.model.Product
import com.android.presentation.ui.component.BannerCard
import com.android.presentation.ui.component.BannerListCard
import com.android.presentation.ui.component.CarouselCard
import com.android.presentation.ui.component.ProductCard
import com.android.presentation.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
    val modelList by viewModel.modelList.collectAsState(
        initial = listOf()
    )
    val columnCount by viewModel.columnCount.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
        items(modelList.size, span = { index ->
            val item = modelList[index]
            val spanCount = getSpanCountByType(item.type, columnCount)
            GridItemSpan(spanCount)
        }) {
            when (val item = modelList[it]) {
                is Banner -> BannerCard(model = item)
                is Product -> ProductCard(product = item) { }
                is BannerList -> BannerListCard(model = item)
                is Carousel -> CarouselCard(model = item)
            }

        }
    }
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when (type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER, ModelType.BANNER_LIST, ModelType.CAROUSEL -> defaultColumnCount
    }
}

