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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.txbeat.jetpacksample.viewmodel.ExtraComponentsViewModel

@Composable
fun ExtraComponentsScreen(viewModel: ExtraComponentsViewModel = viewModel()) {
    val showDialog by viewModel.dialogShown.collectAsState()
    val itemsList = listOf("Item One", "Item Two", "Item Three", "Item Four")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("ExtraComponentsColumn")
    ) {
        Button(onClick = { viewModel.showDialog() }, modifier = Modifier.testTag("ShowAlertButton")) {
            Text("Show AlertDialog", modifier = Modifier.testTag("ShowAlertButtonText"))
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.dismissDialog() },
                title = { Text("Alert", modifier = Modifier.testTag("AlertTitle")) },
                text = { Text("This is an AlertDialog in Jetpack Compose.", modifier = Modifier.testTag("AlertText")) },
                confirmButton = {
                    Button(onClick = { viewModel.dismissDialog() }, modifier = Modifier.testTag("AlertConfirmButton")) {
                        Text("OK", modifier = Modifier.testTag("AlertConfirmText"))
                    }
                },
                modifier = Modifier.testTag("AlertDialog")
            )
        }

        Spacer(modifier = Modifier.height(24.dp).testTag("SpacerAfterAlert"))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("InfoCard"),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp).testTag("InfoCardColumn")) {
                Text("Card Title", style = MaterialTheme.typography.titleLarge, modifier = Modifier.testTag("CardTitle"))
                Text("This is a simple card with some text content.", modifier = Modifier.testTag("CardDescription"))
            }
        }

        Spacer(modifier = Modifier.height(24.dp).testTag("SpacerAfterCard"))

        Text("LazyColumn Example", style = MaterialTheme.typography.titleMedium, modifier = Modifier.testTag("LazyColumnTitle"))
        LazyColumn(modifier = Modifier.testTag("LazyColumn")) {
            items(itemsList) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .testTag("LazyColumnItem_$item")
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp).testTag("SpacerAfterLazyColumn"))

        Text("LazyVerticalGrid Example", style = MaterialTheme.typography.titleMedium, modifier = Modifier.testTag("LazyGridTitle"))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .testTag("LazyVerticalGrid"),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(itemsList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("GridItemCard_$item"),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .testTag("GridItemBox_$item"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = item, modifier = Modifier.testTag("GridItemText_$item"))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp).testTag("SpacerAfterGrid"))

        Text("Image Example", style = MaterialTheme.typography.titleMedium, modifier = Modifier.testTag("ImageExampleTitle"))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("ImageExampleBox"),
            contentAlignment = Alignment.Center
        ) {
            Text("Image Placeholder (add image in res/drawable)", modifier = Modifier.testTag("ImagePlaceholderText"))
        }
    }
}