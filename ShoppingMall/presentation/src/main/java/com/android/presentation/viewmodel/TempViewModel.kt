package com.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.android.domain.model.TempModel
import com.android.domain.usecase.TempUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TempViewModel @Inject constructor(private val useCase: TempUseCase): ViewModel() {
    fun getTempModel(): TempModel {
        return useCase.getTempModel()
    }
}