package com.android.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.data.db.converter.LikeConverter
import com.android.domain.model.Category
import com.android.domain.model.Price
import com.android.domain.model.Product
import com.android.domain.model.SalesStatus
import com.android.domain.model.Shop


@Entity(tableName = "like")
@TypeConverters(LikeConverter::class)
data class LikeProductEntity (
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category,
    val shop: Shop,
    val isNew: Boolean,
    val isFreeShipping: Boolean
)
fun LikeProductEntity.toDomainModel() : Product {
    return Product(
        productId = productId,
        productName = productName,
        imageUrl = imageUrl,
        price = price,
        category = category,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping
    )
}