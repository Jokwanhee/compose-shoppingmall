package com.android.presentation.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.android.domain.model.Banner
import com.android.domain.model.BannerList
import com.android.domain.model.Carousel
import com.android.domain.model.ModelType
import com.android.domain.model.Product
import com.android.domain.model.Ranking
import com.android.presentation.model.BannerListVM
import com.android.presentation.model.BannerVM
import com.android.presentation.model.CarouselVM
import com.android.presentation.model.ProductVM
import com.android.presentation.model.RankingVM
import com.android.presentation.ui.component.BannerCard
import com.android.presentation.ui.component.BannerListCard
import com.android.presentation.ui.component.CarouselCard
import com.android.presentation.ui.component.ProductCard
import com.android.presentation.ui.component.RankingCard
import com.android.presentation.viewmodel.MainViewModel

@Composable
fun MainHomeScreen(viewModel: MainViewModel) {
    val modelList by viewModel.modelList.collectAsState(
        initial = listOf()
    )
    val columnCount by viewModel.columnCount.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
        items(modelList.size, span = { index ->
            val item = modelList[index]
            val spanCount = getSpanCountByType(item.model.type, columnCount)
            GridItemSpan(spanCount)
        }) {
            when (val item = modelList[it]) {
                is BannerVM -> BannerCard(presentationVM = item)
                is BannerListVM -> BannerListCard(presentationVM = item)
                is ProductVM -> ProductCard(presentationVM = item)
                is CarouselVM -> CarouselCard(presentationVM = item)
                is RankingVM -> RankingCard(presentationVM = item)
            }
        }
    }
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when (type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER, ModelType.BANNER_LIST, ModelType.CAROUSEL, ModelType.RANKING -> defaultColumnCount
    }
}

