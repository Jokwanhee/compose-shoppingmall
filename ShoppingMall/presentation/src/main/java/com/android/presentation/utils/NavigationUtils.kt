package com.android.presentation.utils

import android.net.Uri
import android.os.Parcelable
import androidx.navigation.NavHostController
import com.android.domain.model.Category
import com.android.domain.model.Product
import com.google.gson.Gson

object NavigationUtils {
    fun navigate(
        controller: NavHostController,
        routeName: String,
        args: Any? = null,
        backStackRouteName: String? = null,
        isLaunchSingleTop: Boolean = true,
        needToRestoreState: Boolean = true
    ) {
        var argument = ""
        if (args != null) {
            when (args) {
                is Parcelable -> {
                    argument = String.format("/%s", Uri.parse(Gson().toJson(args)))
                }
                is Category -> {
                    argument = String.format("/%s", Uri.parse(Gson().toJson(args)))
                }
                is Product -> {
                    argument = String.format("/%s", args.productId)
                }
            }
        }
        controller.navigate("$routeName$argument") {
            if (backStackRouteName != null) {
                popUpTo(backStackRouteName) { saveState = true }
            }
            launchSingleTop = isLaunchSingleTop
            restoreState = needToRestoreState
        }
    }
}