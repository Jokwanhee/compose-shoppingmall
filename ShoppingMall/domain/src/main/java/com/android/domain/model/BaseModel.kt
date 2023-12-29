package com.android.domain.model

abstract class BaseModel {
    abstract val type: ModelType
}

enum class ModelType {
    PRODUCT,
    BANNER,
    BANNER_LIST,
    CAROUSEL,
    RANKING
}