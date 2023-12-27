package com.android.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.android.presentation.ui.theme.ShoppingMallTheme
import com.android.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private val viewModel: TempViewModel by viewModels()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Toast.makeText(this, "Temp ${viewModel.getTempModel().name}", Toast.LENGTH_SHORT).show()
        setContent {
            ShoppingMallTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
        viewModel.updateColumnCount(getColumnCount())
    }

    private fun getColumnCount(): Int {
        return getDisplayWidthDp().toInt() / DEFAULT_COLUMN_SIZE
    }

    private fun getDisplayWidthDp(): Float {
        return resources.displayMetrics.run { widthPixels / density }
    }

    companion object {
        private const val DEFAULT_COLUMN_SIZE = 120
    }
}