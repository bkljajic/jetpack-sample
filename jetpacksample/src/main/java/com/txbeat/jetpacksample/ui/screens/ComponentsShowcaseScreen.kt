package com.example.composecatalog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsShowcaseScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    var sliderPosition by remember { mutableStateOf(0f) }
    var rangeSliderPosition by remember { mutableStateOf(0f..100f) }
    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var drawerOpen by remember { mutableStateOf(false) }
    val showSheet = remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val datePickerState = rememberDatePickerState()
    val selectedDateMillis = datePickerState.selectedDateMillis






    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Compose Catalog",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = { /* Handle navigation */ }
                )
                NavigationDrawerItem(
                    label = { Text("Settings") },
                    selected = false,
                    onClick = { /* Handle navigation */ }
                )
            }
        }
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar = {
                TopAppBar(
                    title = { Text("Components Showcase") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }, bottomBar = {
                BottomAppBar {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding() // <-- This ensures content isn't obscured
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Bottom Bar", style = MaterialTheme.typography.labelLarge)
                        IconButton(onClick = { /* TODO */ }) {
                            Icon(Icons.Default.Favorite, contentDescription = "Fav")
                        }
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { /* FAB Action */ }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).padding(16.dp)
                    .verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Buttons
                Text("Buttons", style = MaterialTheme.typography.titleMedium)
                Button(onClick = { /*TODO*/ }) { Text("Filled Button") }
                OutlinedButton(onClick = { /*TODO*/ }) { Text("Outlined Button") }
                TextButton(onClick = { /*TODO*/ }) { Text("Text Button") }
                FilledTonalButton(onClick = { /* Tonal */ }) {
                    Text("Filled Tonal Button")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Icon Button")
                }
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Add, contentDescription = "FAB")
                }
                ExtendedFloatingActionButton(
                    onClick = { /*TODO*/ },
                    icon = { Icon(Icons.Default.Place, contentDescription = "Extended FAB") },
                    text = { Text("Extended FAB") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // TextFields
                Text("TextFields", style = MaterialTheme.typography.titleMedium)
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Filled TextField") })
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Outlined TextField") })


                Spacer(modifier = Modifier.height(16.dp))

                // Checkboxes
                Text("Checkboxes", style = MaterialTheme.typography.titleMedium)
                Checkbox(checked = checked, onCheckedChange = { checked = it })
                TriStateCheckbox(
                    state = androidx.compose.ui.state.ToggleableState.Indeterminate,
                    onClick = { /*TODO*/ })

                Spacer(modifier = Modifier.height(16.dp))

                // Switches
                Text("Switches", style = MaterialTheme.typography.titleMedium)
                var switchOn by remember { mutableStateOf(false) }
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Switch(checked = switchOn, onCheckedChange = { switchOn = it })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Switch is ${if (switchOn) "ON" else "OFF"}")
                }

                Spacer(modifier = Modifier.height(16.dp))
                // Chips
                Card {
                    Column(Modifier.padding(16.dp)) {
                        Text("Chips", style = MaterialTheme.typography.titleMedium)

                        // AssistChip
                        AssistChip(
                            onClick = { /* TODO */ },
                            label = { Text("Assist Chip") }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // FilterChip
                        var selected by remember { mutableStateOf(false) }
                        FilterChip(
                            selected = selected,
                            onClick = { selected = !selected },
                            label = { Text(if (selected) "Selected" else "Filter Chip") }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // InputChip
                        var inputChipSelected by remember { mutableStateOf(true) }
                        InputChip(
                            selected = inputChipSelected,
                            onClick = { inputChipSelected = !inputChipSelected },
                            label = { Text("Input Chip") },
                            trailingIcon = {
                                if (inputChipSelected) {
                                    IconButton(onClick = { inputChipSelected = false }) {
                                        Icon(Icons.Default.Favorite, contentDescription = "Remove")
                                    }
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // SuggestionChip (simulated using AssistChip)
                        AssistChip(
                            onClick = { /* maybe autofill something */ },
                            label = { Text("Suggestion Chip") }
                        )
                    }
                }

                // Cards
                Card {
                    Column(Modifier.padding(16.dp)) {
                        Text("Cards", style = MaterialTheme.typography.titleMedium)

                        Spacer(modifier = Modifier.height(8.dp))

                        // Filled Card (default)
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text("Filled Card", style = MaterialTheme.typography.bodyLarge)
                                Text("This is the default Material3 card.")
                            }
                        }

                        // Elevated Card
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text("Elevated Card", style = MaterialTheme.typography.bodyLarge)
                                Text("Elevated cards cast a shadow.")
                            }
                        }

                        // Outlined Card
                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text("Outlined Card", style = MaterialTheme.typography.bodyLarge)
                                Text("Outlined cards have a border but no elevation.")
                            }
                        }
                    }
                }
// Dialogs
                Card {
                    Column(Modifier.padding(16.dp)) {
                        Text("Dialogs", style = MaterialTheme.typography.titleMedium)

                        var showAlertDialog by remember { mutableStateOf(false) }
                        var showCustomDialog by remember { mutableStateOf(false) }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = { showAlertDialog = true }) {
                            Text("Show AlertDialog")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = { showCustomDialog = true }) {
                            Text("Show Custom Dialog")
                        }

                        // AlertDialog
                        if (showAlertDialog) {
                            AlertDialog(
                                onDismissRequest = { showAlertDialog = false },
                                title = { Text("Alert") },
                                text = { Text("This is a Material3 AlertDialog.") },
                                confirmButton = {
                                    TextButton(onClick = { showAlertDialog = false }) {
                                        Text("OK")
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = { showAlertDialog = false }) {
                                        Text("Cancel")
                                    }
                                }
                            )
                        }

                        // Custom Dialog
                        if (showCustomDialog) {
                            Dialog(onDismissRequest = { showCustomDialog = false }) {
                                Surface(
                                    shape = MaterialTheme.shapes.medium,
                                    tonalElevation = 8.dp
                                ) {
                                    Column(
                                        modifier = Modifier.padding(24.dp),
                                        verticalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        Text("This is a custom dialog layout.")
                                        Button(onClick = { showCustomDialog = false }) {
                                            Text("Close")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                // Sliders
                Text("Sliders", style = MaterialTheme.typography.titleMedium)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Continuous Slider: ${(sliderPosition * 100).toInt()}")
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("Discrete Slider (steps = 4)")
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        steps = 4,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("Range Slider: ${rangeSliderPosition.start.toInt()} - ${rangeSliderPosition.endInclusive.toInt()}")
                    RangeSlider(
                        value = rangeSliderPosition,
                        onValueChange = { rangeSliderPosition = it },
                        valueRange = 0f..100f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .height(56.dp) // 🔥 Prevent clipping and overlap
                    )
                }

                // Snackbar
                Card {
                    Column(Modifier.padding(16.dp)) {
                        Text("Snackbar", style = MaterialTheme.typography.titleMedium)

                        Button(onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "This is a snackbar!",
                                    actionLabel = "Dismiss"
                                )
                            }
                        }) {
                            Text("Show Snackbar")
                        }
                    }
                }


                // Progress Indicators
                Text("Progress Indicators", style = MaterialTheme.typography.titleMedium)
                Text("Linear")
                LinearProgressIndicator(progress = sliderPosition.coerceIn(0f, 1f))
                Spacer(modifier = Modifier.height(8.dp))
                Text("Circular")
                CircularProgressIndicator(progress = sliderPosition.coerceIn(0f, 1f))
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { showSheet.value = true }) {
                    Text("Show Bottom Sheet")
                }

                if (showSheet.value) {
                    ModalBottomSheet(
                        onDismissRequest = { showSheet.value = false },
                        sheetState = bottomSheetState
                    ) {
                        Column(Modifier.padding(16.dp)            .navigationBarsPadding() // <-- This ensures content isn't obscured
                        ) {
                            Text("This is a bottom sheet!")
                            Button(onClick = { showSheet.value = false }) {
                                Text("Close")
                            }
                        }
                    }
                }
                BadgedBox(
                    badge = {
                        Badge {
                            Text("3")
                        }
                    }
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                }
                Button(onClick = { showDatePicker = true }) {
                    Text("Pick Date")
                }

                Text("Selected: ${selectedDate ?: "None"}")
                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = { showDatePicker = false }) {
                                Text("OK")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }

            }
        }
    }




}