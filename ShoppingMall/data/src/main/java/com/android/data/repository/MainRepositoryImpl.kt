package com.android.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.android.domain.model.Product
import com.android.domain.repository.MainRepository
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.InputStreamReader
import java.lang.reflect.Type
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : MainRepository {
    override fun getProductList(): Flow<List<Product>> = flow {
        val inputStream = context.assets.open("product_list.json")
        val inputStreamReader = InputStreamReader(inputStream)
        val jsonString = inputStreamReader.readText()
        val type = object : TypeToken<List<Product>>() {}.type

        emit(GsonBuilder().create().fromJson(jsonString, type))
    }
}
