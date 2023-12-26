package com.android.domain.usecase

import com.android.domain.model.TempModel
import com.android.domain.repository.TempRepository
import javax.inject.Inject

class TempUseCase @Inject constructor(private val repository: TempRepository) {
    fun getTempModel(): TempModel {
        return repository.getTempModel()
    }
}