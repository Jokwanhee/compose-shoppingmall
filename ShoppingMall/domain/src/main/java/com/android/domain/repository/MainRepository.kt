package com.android.domain.repository

import com.android.domain.model.BaseModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getModelList(): Flow<List<BaseModel>>
}