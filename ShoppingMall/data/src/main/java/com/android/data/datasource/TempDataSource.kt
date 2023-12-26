package com.android.data.datasource

import com.android.domain.model.TempModel
import javax.inject.Inject

class TempDataSource @Inject constructor() {
    fun getTempModel(): TempModel {
        return TempModel("temp model")
    }
}