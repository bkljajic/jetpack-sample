
package com.txbeat.jetpacksample.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composecatalog.ui.screens.ComponentsShowcaseScreen
import com.txbeat.jetpacksample.ui.screens.ExtraComponentsScreen
import com.txbeat.jetpacksample.ui.screens.FullScreenMultiBrowseCarouselScreen
import com.txbeat.jetpacksample.ui.screens.HomeScreen
import com.txbeat.jetpacksample.ui.screens.MissingComponentsScreen
import com.txbeat.jetpacksample.ui.screens.MultiBrowseCarouselScreen
import com.txbeat.jetpacksample.ui.screens.SearchOnlyScreen


@Composable
fun NavGraph(navController: NavHostController, padding: PaddingValues) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController) // ✅ Pass it here
        }
        composable("extras") {
            ExtraComponentsScreen() // If needed
        }
        composable("missing") {
            MissingComponentsScreen() // If needed
        }
        composable("search_only") {
            SearchOnlyScreen()
        }

        composable("carousel") {
            MultiBrowseCarouselScreen()
        }
        composable("fullscreen_multi_browse") {
            FullScreenMultiBrowseCarouselScreen()
        }
        composable("components_showcase") { ComponentsShowcaseScreen(navController) }

    }
}
