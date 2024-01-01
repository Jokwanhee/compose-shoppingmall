package com.android.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.android.domain.model.Category
import com.android.domain.model.Product
import com.android.presentation.ui.NavigationRouteName.CATEGORY
import com.android.presentation.ui.NavigationRouteName.MAIN_CATEGORY
import com.android.presentation.ui.NavigationRouteName.MAIN_HOME
import com.android.presentation.ui.NavigationRouteName.MAIN_MYPAGE
import com.android.presentation.ui.NavigationRouteName.PRODUCT_DETAIL

sealed class NavigationItem(open val route: String) {
    sealed class MainNav(
        override val route: String,
        val icon: ImageVector,
        val name: String
    ) : NavigationItem(route) {
        object Home : MainNav(MAIN_HOME, Icons.Filled.Home, MAIN_HOME)
        object Category : MainNav(MAIN_CATEGORY, Icons.Filled.Star, MAIN_CATEGORY)
        object MyPage : MainNav(MAIN_MYPAGE, Icons.Filled.AccountBox, MAIN_MYPAGE)
        companion object {
            fun isMainRoute(route: String?): Boolean {
                return when (route) {
                    MAIN_HOME, MAIN_CATEGORY, MAIN_MYPAGE -> true
                    else -> false
                }
            }
        }
    }

    data class CategoryNav(
        val category: Category
    ) : NavigationItem(CATEGORY)

    data class ProductDetailNav(val product: Product) : NavigationItem(PRODUCT_DETAIL)
}

object NavigationRouteName {
    const val MAIN_HOME = "main_home"
    const val MAIN_CATEGORY = "main_category"
    const val MAIN_MYPAGE = "main_mypage"
    const val CATEGORY = "category"
    const val PRODUCT_DETAIL = "product_detail"
}