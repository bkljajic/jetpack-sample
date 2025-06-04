package com.txbeat.jetpacksample.ui.screens

import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.txbeat.jetpacksample.R
import com.example.composecatalog.viewmodel.MissingComponentsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MissingComponentsScreen(viewModel: MissingComponentsViewModel = viewModel()) {
    val context = LocalContext.current
    val carouselItems = listOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground
    )

    val tabItemsWithIcons = listOf(
        "Home" to Icons.Default.Home,
        "Search" to Icons.Default.Search,
        "Profile" to Icons.Default.Person
    )
    val tabItemsTextOnly = listOf("Overview", "Details", "Settings")
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Scaffold(modifier = Modifier.testTag("MissingComponentsScaffold")) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState)
                .testTag("MissingComponentsColumn")
        ) {
            Spacer(modifier = Modifier.height(48.dp).testTag("SpacerTop"))

            TabRow(selectedTabIndex = viewModel.selectedTab.value, modifier = Modifier.testTag("TabRowIcons")) {
                tabItemsWithIcons.forEachIndexed { index, (label, icon) ->
                    Tab(
                        selected = viewModel.selectedTab.value == index,
                        onClick = { viewModel.selectTab(index) },
                        text = { Text(label, modifier = Modifier.testTag("TabIconLabel_$label")) },
                        icon = { Icon(icon, contentDescription = label, modifier = Modifier.testTag("TabIcon_$label")) },
                        modifier = Modifier.testTag("TabIcon_$index")
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp).testTag("SpacerTabs"))

            TabRow(selectedTabIndex = viewModel.selectedTextOnlyTab.value, modifier = Modifier.testTag("TabRowTextOnly")) {
                tabItemsTextOnly.forEachIndexed { index, label ->
                    Tab(
                        selected = viewModel.selectedTextOnlyTab.value == index,
                        onClick = { viewModel.selectTextOnlyTab(index) },
                        text = { Text(label, modifier = Modifier.testTag("TabTextOnlyLabel_$label")) },
                        modifier = Modifier.testTag("TabTextOnly_$index")
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp).testTag("SpacerAfterTabs"))

            ElevatedButton(onClick = { /* Action */ }, modifier = Modifier.testTag("ElevatedButton")) {
                Text("Elevated Button", modifier = Modifier.testTag("ElevatedButtonText"))
            }

            Spacer(modifier = Modifier.height(16.dp).testTag("SpacerAfterButton"))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .testTag("MenuRow"),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Menu", style = MaterialTheme.typography.titleMedium, modifier = Modifier.testTag("MenuTitle"))
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier.testTag("MenuBox")) {
                    IconButton(onClick = { viewModel.expandMenu() }, modifier = Modifier.testTag("MenuIconButton")) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu", modifier = Modifier.testTag("MenuIcon"))
                    }
                    DropdownMenu(
                        expanded = viewModel.menuExpanded.value,
                        onDismissRequest = { viewModel.dismissMenu() },
                        modifier = Modifier.testTag("DropdownMenu")
                    ) {
                        DropdownMenuItem(text = { Text("Menu 1", modifier = Modifier.testTag("MenuItem1Text")) }, onClick = { viewModel.dismissMenu() }, modifier = Modifier.testTag("MenuItem1") )
                        DropdownMenuItem(text = { Text("Menu 2", modifier = Modifier.testTag("MenuItem2Text")) }, onClick = { viewModel.dismissMenu() }, modifier = Modifier.testTag("MenuItem2") )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp).testTag("SpacerAfterMenu"))

            Column(modifier = Modifier.testTag("RadioGroup")) {
                listOf("Option 1", "Option 2", "Option 3").forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .testTag("RadioRow_$option")
                    ) {
                        RadioButton(
                            selected = viewModel.selectedRadio.value == option,
                            onClick = { viewModel.selectRadio(option) },
                            modifier = Modifier.testTag("Radio_$option")
                        )
                        Text(option, modifier = Modifier.padding(start = 8.dp).testTag("RadioText_$option"))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp).testTag("SpacerAfterRadio"))

            ElevatedButton(onClick = {
                TimePickerDialog(
                    context,
                    { _: TimePicker, hour: Int, minute: Int ->
                        viewModel.updateTime(String.format("%02d:%02d", hour, minute))
                    },
                    12, 0, true
                ).show()
            }, modifier = Modifier.testTag("TimePickerButton")) {
                Text("Pick Time", modifier = Modifier.testTag("TimePickerButtonText"))
            }

            if (viewModel.selectedTime.value.isNotEmpty()) {
                Text("Selected Time: ${viewModel.selectedTime.value}", modifier = Modifier.testTag("SelectedTimeText"))
            }

            Spacer(modifier = Modifier.height(16.dp).testTag("SpacerAfterTime"))

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Example Image",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
                    .testTag("ExampleImage"),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )

            Spacer(modifier = Modifier.height(16.dp).testTag("SpacerAfterImage"))

            Text("Carousel", style = MaterialTheme.typography.titleMedium, modifier = Modifier.testTag("CarouselTitle"))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().testTag("CarouselRow")
            ) {
                items(carouselItems) { imageRes ->
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(140.dp)
                            .padding(4.dp)
                            .testTag("CarouselImage_$imageRes")
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp).testTag("SpacerBottom"))
        }
    }
}