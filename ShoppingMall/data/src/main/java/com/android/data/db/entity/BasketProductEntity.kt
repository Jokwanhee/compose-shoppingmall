package com.android.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.data.db.converter.BasketConverter
import com.android.domain.model.Category
import com.android.domain.model.Price
import com.android.domain.model.Product
import com.android.domain.model.SalesStatus
import com.android.domain.model.Shop

@Entity(tableName = "basket")
@TypeConverters(BasketConverter::class)
data class BasketProductEntity(
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category,
    val salesStatus: SalesStatus,
    val shop: Shop,
    val isNew: Boolean,
    val isFreeShipping: Boolean
)
fun BasketProductEntity.toDomainModel() : Product {
    return Product(
        productId = productId,
        productName = productName,
        imageUrl = imageUrl,
        price = price,
        category = category,
        salesStatus = salesStatus,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping
    )
}
