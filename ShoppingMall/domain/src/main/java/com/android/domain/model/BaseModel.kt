package com.android.domain.model

sealed class BaseModel {
    abstract val type: ModelType
}

enum class ModelType {
    PRODUCT,
    BANNER,
    BANNER_LIST,
    CAROUSEL,
    RANKING
}