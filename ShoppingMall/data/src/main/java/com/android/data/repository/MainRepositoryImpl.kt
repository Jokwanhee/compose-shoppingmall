package com.android.data.repository

import android.content.Context
import com.android.data.deserializer.BaseModelDeserializer
import com.android.domain.model.BaseModel
import com.android.domain.repository.MainRepository
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.InputStreamReader
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : MainRepository {
    override fun getModelList(): Flow<List<BaseModel>> = flow {
        val inputStream = context.assets.open("product_list.json")
        val inputStreamReader = InputStreamReader(inputStream)
        val jsonString = inputStreamReader.readText()
        val type = object : TypeToken<List<BaseModel>>() {}.type

        emit(
            GsonBuilder()
                .registerTypeAdapter(BaseModel::class.java, BaseModelDeserializer())
                .create()
                .fromJson(jsonString, type)
        )
    }
}
