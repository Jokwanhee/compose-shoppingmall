package com.android.presentation.ui.main

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.android.presentation.ui.common.ProductCard
import com.android.presentation.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
    val productList by viewModel.productList.collectAsState(
        initial = listOf()
    )
    val columnCount by viewModel.columnCount.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
        items(productList.size) {
            if (productList[it].productId != null) {
                ProductCard(product = productList[it]) {
                    // handle product click
                }
            }
        }
    }
    Text(text = "Hello Main")
}