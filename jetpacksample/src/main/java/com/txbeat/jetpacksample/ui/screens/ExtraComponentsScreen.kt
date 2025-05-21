
package com.txbeat.jetpacksample.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.txbeat.jetpacksample.viewmodel.ExtraComponentsViewModel

@Composable
fun ExtraComponentsScreen(viewModel: ExtraComponentsViewModel = viewModel()) {
    val showDialog by viewModel.dialogShown.collectAsState()
    val itemsList = listOf("Item One", "Item Two", "Item Three", "Item Four")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Button(onClick = { viewModel.showDialog() }) {
            Text("Show AlertDialog")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.dismissDialog() },
                title = { Text("Alert") },
                text = { Text("This is an AlertDialog in Jetpack Compose.") },
                confirmButton = {
                    Button(onClick = { viewModel.dismissDialog() }) {
                        Text("OK")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Card Title", style = MaterialTheme.typography.titleLarge)
                Text("This is a simple card with some text content.")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("LazyColumn Example", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(itemsList) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text("LazyVerticalGrid Example", style = MaterialTheme.typography.titleMedium)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), // or wrap it in a scrollable Column to avoid clipping
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(itemsList) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = item)
                    }
                }
            }
        }



        Spacer(modifier = Modifier.height(24.dp))

        Text("Image Example", style = MaterialTheme.typography.titleMedium)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text("Image Placeholder (add image in res/drawable)")
        }
    }
}
