package com.android.data.model

import com.android.domain.model.TempModel

data class TestModelResponse(
    val name: String?
)

fun TestModelResponse.toDomainModel(): TempModel? {
    if (name != null ) return TempModel(this.name)
    return null
}
