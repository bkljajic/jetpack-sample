package com.txbeat.jetpacksample.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.ui.platform.testTag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchOnlyScreen() {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val items = listOf("Antelope", "Bear", "Cat", "Dog", "Elephant", "Fox", "Giraffe")
    val filteredItems = items.filter { it.contains(query, ignoreCase = true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = active) { active = false }
            .padding(top = 80.dp)
            .testTag("SearchOnlyContainer")
    ) {
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = { active = false },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text("Search...") },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag("SearchBar")
        ) {
            if (filteredItems.isEmpty()) {
                Text(
                    "No results",
                    modifier = Modifier
                        .padding(16.dp)
                        .testTag("SearchNoResults"),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("SearchResultsColumn")
                ) {
                    filteredItems.forEach { item ->
                        ListItem(
                            headlineContent = { Text(item) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    query = item
                                    active = false
                                }
                                .padding(horizontal = 8.dp)
                                .testTag("SearchResult_$item")
                        )
                    }
                }
            }
        }
    }
}
