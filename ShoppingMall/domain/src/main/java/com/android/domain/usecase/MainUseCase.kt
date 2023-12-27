package com.android.domain.usecase

import com.android.domain.model.BaseModel
import com.android.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun getModelList(): Flow<List<BaseModel>> {
        return mainRepository.getModelList()
    }
}