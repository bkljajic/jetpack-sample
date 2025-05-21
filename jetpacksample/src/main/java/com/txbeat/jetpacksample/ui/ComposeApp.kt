
package com.txbeat.jetpacksample.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.txbeat.jetpacksample.navigation.NavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeApp() {
    ComposeCatalogTheme {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Compose Catalog") }
                )
            }
        ) { innerPadding ->
            NavGraph(navController = navController, padding = innerPadding)
        }
    }
}
