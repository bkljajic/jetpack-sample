package com.txbeat.jetpacksample.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.platform.testTag
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
            .verticalScroll(rememberScrollState())
            .testTag("HomeScreenColumn"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Text Example",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth().testTag("TextExampleTitle")
        )

        TextField(
            value = text,
            onValueChange = { viewModel.onTextChange(it) },
            label = { Text("Enter text", modifier = Modifier.testTag("TextFieldLabel")) },
            modifier = Modifier.fillMaxWidth().testTag("TextField")
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
                .padding(vertical = 12.dp)
                .testTag("CombinedButton"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Button",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.testTag("CombinedButtonText")
            )
        }

        Checkbox(
            checked = checked,
            onCheckedChange = { viewModel.onCheckedChange(it) },
            modifier = Modifier.testTag("Checkbox")
        )
        Text("Checkbox is ${if (checked) "checked" else "unchecked"}", modifier = Modifier.testTag("CheckboxStatus"))

        Slider(
            value = sliderValue,
            onValueChange = { viewModel.onSliderChange(it) },
            modifier = Modifier.testTag("Slider")
        )
        Text("Slider value: ${(sliderValue * 100).toInt()}", modifier = Modifier.testTag("SliderValueText"))

        Button(onClick = { navController.navigate("extras") }, modifier = Modifier.testTag("ExtrasButton")) {
            Text("Go to Extra Components", modifier = Modifier.testTag("ExtrasButtonText"))
        }

        Button(onClick = { navController.navigate("components_showcase") }, modifier = Modifier.testTag("ComponentsShowcaseButton")) {
            Text("Go to Components Showcase", modifier = Modifier.testTag("ComponentsShowcaseButtonText"))
        }

        Button(onClick = { navController.navigate("missing") }, modifier = Modifier.testTag("MissingComponentsButton")) {
            Text("Go to Missing Components", modifier = Modifier.testTag("MissingComponentsButtonText"))
        }

        Button(onClick = { navController.navigate("search_only") }, modifier = Modifier.testTag("SearchOnlyButton")) {
            Text("Go to Search Only Screen", modifier = Modifier.testTag("SearchOnlyButtonText"))
        }

        Button(onClick = { navController.navigate("fullscreen_multi_browse") }, modifier = Modifier.testTag("FullScreenCarouselButton")) {
            Text("Go to Full screen Carousel", modifier = Modifier.testTag("FullScreenCarouselButtonText"))
        }

        Button(onClick = { navController.navigate("carousel") }, modifier = Modifier.testTag("MultiBrowseCarouselButton")) {
            Text("Go to Multi-Browse Carousel", modifier = Modifier.testTag("MultiBrowseCarouselButtonText"))
        }
    }
}