package com.android.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.domain.model.Carousel
import com.android.domain.model.Price
import com.android.domain.model.Product
import com.android.presentation.R
import com.android.presentation.model.CarouselVM
import com.android.presentation.model.PresentationVM

@Composable
fun CarouselCard(navHostController: NavHostController, presentationVM: CarouselVM) {
    val scrollState = rememberLazyListState()
    Column {
        Text(
            text = presentationVM.model.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(10.dp)
        )
        LazyRow(
            state = scrollState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(presentationVM.model.productList.size) {
                CarouselProductCard(product = presentationVM.model.productList[it]) {
                    presentationVM.openCarouselProduct(navHostController, it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselProductCard(product: Product, onClick: (Product) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .width(150.dp)
            .wrapContentHeight()
            .padding(10.dp),
        onClick = { onClick(product) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Text(
                text = product.productName,
                fontSize = 14.sp,
            )
            Price(product)
        }
    }
}