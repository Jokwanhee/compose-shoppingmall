package com.android.data.repository

import com.android.data.datasource.TempDataSource
import com.android.data.model.toDomainModel
import com.android.domain.model.TempModel
import com.android.domain.repository.TempRepository
import javax.inject.Inject

class TempRepositoryImpl @Inject constructor(private val dataSource: TempDataSource): TempRepository {
    override fun getTempModel(): TempModel {
        return dataSource.getTempModel()
    }
}