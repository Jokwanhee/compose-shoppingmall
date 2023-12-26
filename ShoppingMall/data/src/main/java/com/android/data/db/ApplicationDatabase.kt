package com.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.data.db.dao.BasketDao
import com.android.data.db.dao.LikeDao
import com.android.data.db.dao.PurchaseDao
import com.android.data.db.entity.BasketProductEntity
import com.android.data.db.entity.LikeProductEntity
import com.android.data.db.entity.PurchaseProductEntity

@Database(
    entities = [
        PurchaseProductEntity::class,
        LikeProductEntity::class,
        BasketProductEntity::class
    ],
    version = 1
)
abstract class ApplicationDatabase: RoomDatabase() {
    companion object{
        const val DB_NAME = "applicationDatabase.db"
    }

    abstract fun purchaseDao() : PurchaseDao
    abstract fun likeDao() : LikeDao
    abstract fun basketDao() : BasketDao
}