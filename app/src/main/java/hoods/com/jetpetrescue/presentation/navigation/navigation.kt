package hoods.com.jetpetrescue.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import hoods.com.jetpetrescue.presentation.details.DetailScreen
import hoods.com.jetpetrescue.presentation.home.HomeScreen
import hoods.com.jetpetrescue.presentation.viewModel.UiState
import java.lang.Exception

enum class Screen {
    Home,
    Detail,

}

@androidx.compose.runtime.Composable
fun JetPetNavigation(
    navController: NavHostController,
    uiState: UiState,
    onThemeChange: () -> Unit,
    onLoadNextPage: () -> Unit,
    onInfiniteScrollChange: (Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.Home.name) {
        composable(route = Screen.Home.name) {
            HomeScreen(
                uiState = uiState,
                onSwitchClick = onThemeChange,
                onLoadNextPage = onLoadNextPage,
                onInfiniteScrollingChange = onInfiniteScrollChange,
                onPetClick = { petSelected ->
                    navController.navigate("${Screen.Detail.name}/$petSelected")
                },
            )
        }
        composable(
            route = "${Screen.Detail.name}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backstackEntry ->
            val id = backstackEntry.arguments?.getInt("id") ?: 0
            val animal = uiState.animals.data?.get(id)!!
            val context = LocalContext.current
            DetailScreen(
                pet = animal,
                onNavigateBack = {
                    navController.navigateUp()
                },
                onPetBtnClick = {
                    openUrl(
                        context = context,
                        url = animal.url
                    )
                }
            )
        }
    }
}


private fun openUrl(
    context: Context,
    url: String
) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        context.startActivity(intent)
    }catch(err: Exception) {
        err.printStackTrace()
    }
}