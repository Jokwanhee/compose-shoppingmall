package com.android.data.deserializer

import com.android.domain.model.Banner
import com.android.domain.model.BannerList
import com.android.domain.model.BaseModel
import com.android.domain.model.Carousel
import com.android.domain.model.ModelType
import com.android.domain.model.Product
import com.android.domain.model.Ranking
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


/**
 * 역직렬화 과정에서 List<Person> -> List<Object> 으로 역직렬화함
 * 이러한 과정을 상태 enum 클래스로 분기처리 해주기 위해서
 * JsonDeserializer 인터페이스를 상속하는 클래스 생성
 */
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
            ModelType.PRODUCT -> gson.fromJson(root, Product::class.java)
            ModelType.BANNER -> gson.fromJson(root, Banner::class.java)
            ModelType.BANNER_LIST -> gson.fromJson(root, BannerList::class.java)
            ModelType.CAROUSEL -> gson.fromJson(root, Carousel::class.java)
            ModelType.RANKING -> gson.fromJson(root, Ranking::class.java)
        }
    }

    companion object {
        private const val TYPE = "type"
    }
}