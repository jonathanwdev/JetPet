package hoods.com.jetpetrescue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import hoods.com.jetpetrescue.presentation.navigation.JetPetNavigation
import hoods.com.jetpetrescue.presentation.ui.theme.JetPetTheme
import hoods.com.jetpetrescue.presentation.viewModel.MainViewModel





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel(modelClass = MainViewModel::class.java)
            var isDarkTheme by remember {
                mutableStateOf(false)
            }

            var id by remember {
                mutableStateOf(-1)
            }

            val navController = rememberNavController()

            JetPetTheme(
                darkTheme = isDarkTheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    JetPetNavigation(
                        navController = navController,
                        uiState = viewModel.uiState,
                        onThemeChange = { isDarkTheme = !isDarkTheme },
                        onLoadNextPage = { viewModel.loadNextPage() },
                        onInfiniteScrollChange = viewModel::onInfiniteScrollChange
                    )
                }
            }
        }
    }
}
