@file:OptIn(ExperimentalMaterial3Api::class)

package com.android.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.presentation.ui.main.MainInsideScreen
import com.android.presentation.ui.theme.ShoppingMallTheme
import com.android.presentation.viewmodel.MainViewModel

sealed class MainNavigationItem(var route: String, val icon: ImageVector, val name: String)
object Main : MainNavigationItem("Main", Icons.Filled.Home, "Main")
object Category : MainNavigationItem("Category", Icons.Filled.Star, "Category")
object MyPage : MainNavigationItem("MyPage", Icons.Filled.AccountBox, "MyPage")

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

    Scaffold(
        topBar = { Header(viewModel) },
        bottomBar = { MainBottomNavigationBar(navController) },
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
    navController: NavHostController
) {
    val bottomNavigationItems = listOf(Main, Category, MyPage)

    NavigationBar(
        containerColor = Color(0xFF000000),
        contentColor = Color(0xFF93FFDD)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

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
        startDestination = Main.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Main.route) {
            MainInsideScreen(mainViewModel)
        }
        composable(Category.route) {
            Text(text = "Hello Category")
        }
        composable(MyPage.route) {
            Text(text = "Hello MyPage")
        }
    }
}
