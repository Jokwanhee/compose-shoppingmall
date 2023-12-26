package com.android.data.db.converter

import androidx.room.TypeConverter
import com.android.domain.model.Category
import com.android.domain.model.Price
import com.android.domain.model.Shop
import com.google.gson.GsonBuilder

class BasketConverter {
    private val gson = GsonBuilder().create()

    @TypeConverter
    fun fromPrice(value: Price): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPrice(value: String): Price {
        return gson.fromJson(value, Price::class.java)
    }

    @TypeConverter
    fun fromCategory(value: Category): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCategory(value: String): Category {
        return gson.fromJson(value, Category::class.java)
    }

    @TypeConverter
    fun fromShop(value: Shop): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toShop(value: String): Shop {
        return gson.fromJson(value, Shop::class.java)
    }
}