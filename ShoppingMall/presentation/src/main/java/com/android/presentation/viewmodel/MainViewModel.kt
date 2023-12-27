package com.android.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    useCase: MainUseCase
) : ViewModel() {
    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount: StateFlow<Int> = _columnCount.asStateFlow()

    val productList = useCase.getProductList()

    fun openSearchForm() {
        println("openSearchForm")
    }

    fun updateColumnCount(count: Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }
}