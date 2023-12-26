package com.android.domain.repository

import com.android.domain.model.TempModel

interface TempRepository {
    fun getTempModel(): TempModel
}