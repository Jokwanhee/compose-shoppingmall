package com.android.presentation.ui.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.domain.model.Category
import com.android.domain.model.Product
import com.android.presentation.ui.component.ProductCard
import com.android.presentation.viewmodel.category.CategoryViewModel

@Composable
fun CategoryScreen(
    category: Category,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsState()
    LaunchedEffect(key1 = category) {
        // 주의할점!
        // 컴포지션 될 때 호출되는 스코프
        // 키 값이 변경되면 기존 코루틴이 취소되고 다시 호출된다.
        // category 를 변경하면 화면을 계속해서 재구성한다고 이해하면 쉽다.
        viewModel.updateCategory(category)
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(products.size) { index ->
            ProductCard(presentationVM = products[index])
        }
    }
}