
package com.txbeat.jetpacksample.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composecatalog.ui.screens.ComponentsShowcaseScreen
import com.txbeat.jetpacksample.ui.screens.ExtraComponentsScreen
import com.txbeat.jetpacksample.ui.screens.HomeScreen


@Composable
fun NavGraph(navController: NavHostController, padding: PaddingValues) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController) // ✅ Pass it here
        }
        composable("extras") {
            ExtraComponentsScreen() // If needed
        }
        composable("components_showcase") { ComponentsShowcaseScreen(navController) }

    }
}
