package com.android.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.domain.model.Product
import com.android.presentation.R
import com.android.presentation.model.RankingVM
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

private const val DEFAULT_RANKING_ITEM_COUNT = 3

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RankingCard(navHostController: NavHostController, presentationVM: RankingVM) {
    val pagerState = rememberPagerState()
    val pageCount = presentationVM.model.productList.size / DEFAULT_RANKING_ITEM_COUNT

    Column {
        Text(
            text = presentationVM.model.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
        )
        HorizontalPager(
            count = pageCount,
            state = pagerState,
            contentPadding = PaddingValues(0.dp, 0.dp, 50.dp, 0.dp)
        ) { index ->
            Column {
                RankingProductCard(index * 3, presentationVM.model.productList[index * 3]) { product ->
                    presentationVM.openRankingProduct(navHostController, product)
                }
                RankingProductCard(index * 3 + 1, presentationVM.model.productList[index * 3 + 1]){ product ->
                    presentationVM.openRankingProduct(navHostController, product)
                }
                RankingProductCard(index * 3 + 2, presentationVM.model.productList[index * 3 + 2]){ product ->
                    presentationVM.openRankingProduct(navHostController, product)
                }
            }
        }
    }
}

@Composable
fun RankingProductCard(index: Int, product: Product, onClick: (Product) -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "${index + 1}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(80.dp)
                .aspectRatio(0.75f)
        )
        Column(
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Text(
                text = product.shop.shopName,
                fontSize = 14.sp,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
            Text(
                text = product.productName,
                fontSize = 14.sp,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
            Price(product = product)
        }
    }
}