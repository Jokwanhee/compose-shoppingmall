@file:OptIn(ExperimentalMaterial3Api::class)

package com.android.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.domain.model.Category
import com.android.domain.model.Product
import com.android.presentation.product_detail.ProductDetailScreen
import com.android.presentation.ui.category.CategoryScreen
import com.android.presentation.ui.main.MainCategoryScreen
import com.android.presentation.ui.main.MainHomeScreen
import com.android.presentation.ui.theme.ShoppingMallTheme
import com.android.presentation.viewmodel.MainViewModel
import com.google.gson.Gson

sealed class MainNavigationItem(var route: String, val icon: ImageVector, val name: String)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingMallTheme {
        MainScreen()
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = { Header(viewModel) },
        bottomBar = {
            if (NavigationItem.MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        },
    ) { innerPadding ->
        MainNavigationScreen(
            mainViewModel = viewModel,
            navController = navController,
            innerPadding = innerPadding
        )
    }
}

@Composable
fun Header(
    viewModel: MainViewModel
) {
    TopAppBar(
        title = { Text(text = "My App") },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Cyan),
        actions = {
            IconButton(onClick = {
                viewModel.openSearchForm()
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(
    navController: NavHostController,
    currentRoute: String?
) {
    val bottomNavigationItems = listOf(
        NavigationItem.MainNav.Home,
        NavigationItem.MainNav.Category,
        NavigationItem.MainNav.MyPage
    )

    NavigationBar(
        containerColor = Color(0xFF000000),
        contentColor = Color(0xFF93FFDD)
    ) {

        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = item.name,
                        color = Color.White
                    )
                }
            )
        }
    }
}

@Composable
fun MainNavigationScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRouteName.MAIN_HOME,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(NavigationRouteName.MAIN_HOME) {
            MainHomeScreen(navController, mainViewModel)
        }
        composable(NavigationRouteName.MAIN_CATEGORY) {
            MainCategoryScreen(mainViewModel, navController)
        }
        composable(NavigationRouteName.MAIN_MYPAGE) {
            Text(text = "Hello MyPage")
        }
        composable(
            NavigationRouteName.CATEGORY + "/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            val categoryString = it.arguments?.getString("category")
            val category =
                Gson().fromJson(categoryString, Category::class.java)

            if (category != null) {
                CategoryScreen(navHostController = navController, category = category)
            }
        }
        composable(
            NavigationRouteName.PRODUCT_DETAIL + "/{product}",
            arguments = listOf(navArgument("product") { type = NavType.StringType })
        ) {
            val productString = it.arguments?.getString("product")

            if (productString != null) {
                ProductDetailScreen(productString)
            }
        }
    }
}
