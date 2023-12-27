package com.android.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.domain.model.Category
import com.android.domain.model.Price
import com.android.domain.model.Product
import com.android.domain.model.SalesStatus
import com.android.domain.model.Shop
import com.android.presentation.R
import com.android.presentation.ui.theme.Purple80

@Composable
fun ProductCard(
    product: Product,
    onClick: (Product) -> Unit?
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(10.dp)
            .shadow(elevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
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
//                    .wrapContentWidth(Alignment.End)
            )
            Text(
                text = product.shop.shopName,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = product.productName,
                fontSize = 14.sp,
            )
            Price(product)
        }
    }
}

@Composable
private fun Price(product: Product) {
    when (product.price.salesStatus) {
        SalesStatus.ON_SALE -> {
            Text(
                text = "${product.price.originPrice}원",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        SalesStatus.ON_DISCOUNT -> {
            Text(
                text = "${product.price.originPrice}원",
                fontSize = 14.sp,
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )
            Text(
                text = "${product.price.finalPrice}원",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Purple80,
            )
        }

        SalesStatus.SOLD_OUT -> {
            Text(
                text = "판매종료",
                fontSize = 18.sp,
                color = Color(0xFF666666),
            )
        }
    }
}


@Preview
@Composable
private fun PreviewProductCard() {
    ProductCard(
        product = Product(
            productId = "1",
            productName = "상품 이름",
            imageUrl = "",
            price = Price(
                30000,
                30000,
                SalesStatus.ON_SALE
            ),
            category = Category.Top,
            shop = Shop(
                "1",
                "shop name",
                "",
            ),
            isNew = false,
            isFreeShipping = false,
        )
    ) {

    }
}

@Preview
@Composable
private fun PreviewProductCardDiscount() {
    ProductCard(
        product = Product(
            productId = "1",
            productName = "상품 이름",
            imageUrl = "",
            price = Price(
                30000,
                20000,
                SalesStatus.ON_DISCOUNT
            ),
            category = Category.Top,
            shop = Shop(
                "1",
                "shop name",
                "",
            ),
            isNew = false,
            isFreeShipping = false,
        )
    ) {

    }
}

@Preview
@Composable
private fun PreviewProductCardSoldOut() {
    ProductCard(
        product = Product(
            productId = "1",
            productName = "상품 이름",
            imageUrl = "",
            price = Price(
                30000,
                30000,
                SalesStatus.SOLD_OUT
            ),
            category = Category.Top,
            shop = Shop(
                "1",
                "shop name",
                "",
            ),
            isNew = false,
            isFreeShipping = false,
        )
    ) {

    }
}
