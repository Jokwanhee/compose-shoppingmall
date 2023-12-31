package com.android.presentation.delegate

import androidx.navigation.NavHostController
import com.android.domain.model.Category

interface CategoryDelegate {
    fun openCategory(navHostController: NavHostController, category: Category)
}