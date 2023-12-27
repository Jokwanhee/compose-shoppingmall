package com.android.data.deserializer

import android.util.Log
import com.android.domain.model.Banner
import com.android.domain.model.BaseModel
import com.android.domain.model.ModelType
import com.android.domain.model.Product
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class BaseModelDeserializer: JsonDeserializer<BaseModel> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): BaseModel {
        val root = json?.asJsonObject
        val gson = GsonBuilder().create()

        val typeString = root?.get(TYPE)?.asString ?: ""

        return when(ModelType.valueOf(typeString)) {
            ModelType.PRODUCT -> {
                gson.fromJson(root, Product::class.java)
            }
            ModelType.BANNER -> {
                gson.fromJson(root, Banner::class.java)
            }
        }
    }

    companion object {
        private const val TYPE = "type"
    }
}