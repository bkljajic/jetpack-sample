
package com.txbeat.jetpacksample.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composecatalog.viewmodel.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val text by viewModel.text.collectAsState()
    val checked by viewModel.checked.collectAsState()
    val sliderValue by viewModel.sliderValue.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Text Example",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = text,
            onValueChange = { viewModel.onTextChange(it) },
            label = { Text("Enter text") },
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = { navController.navigate("components_showcase") },
                    onLongClick = { navController.navigate("extras") }
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Button",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Checkbox(
            checked = checked,
            onCheckedChange = { viewModel.onCheckedChange(it) }
        )
        Text("Checkbox is ${if (checked) "checked" else "unchecked"}")

        Slider(
            value = sliderValue,
            onValueChange = { viewModel.onSliderChange(it) }
        )
        Text("Slider value: ${(sliderValue * 100).toInt()}")

        Button(onClick = { navController.navigate("extras") }) {
            Text("Go to Extra Components")
        }

        Button(onClick = { navController.navigate("components_showcase") }) {
            Text("Go to Components Showcase")
        }
    }
}
