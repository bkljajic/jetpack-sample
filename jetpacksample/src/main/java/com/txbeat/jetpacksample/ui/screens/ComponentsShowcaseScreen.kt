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
import androidx.compose.ui.platform.testTag
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
        drawerState = drawerState, drawerContent = {
            ModalDrawerSheet(modifier = Modifier.testTag("DrawerSheet")) {
                Text(
                    "Compose Catalog",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(16.dp)
                        .testTag("DrawerTitle")
                )
                NavigationDrawerItem(
                    label = { Text("Home", modifier = Modifier.testTag("DrawerItemHomeText")) },
                    selected = false,
                    onClick = { /* Handle navigation */ },
                    modifier = Modifier.testTag("DrawerItemHome")
                )
                NavigationDrawerItem(
                    label = {
                        Text(
                            "Settings", modifier = Modifier.testTag("DrawerItemSettingsText")
                        )
                    },
                    selected = false,
                    onClick = { /* Handle navigation */ },
                    modifier = Modifier.testTag("DrawerItemSettings")
                )
            }
        }, modifier = Modifier.testTag("ModalNavigationDrawer")
    ) {
        Scaffold(snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState, modifier = Modifier.testTag("SnackbarHost")
            )
        }, topBar = {
            TopAppBar(title = {
                Text(
                    "Components Showcase", modifier = Modifier.testTag("TopAppBarTitle")
                )
            }, navigationIcon = {
                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier.testTag("TopAppBarBackButton")
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.testTag("TopAppBarBackIcon")
                    )
                }
            }, modifier = Modifier.testTag("TopAppBar")
            )
        }, bottomBar = {
            BottomAppBar(modifier = Modifier.testTag("BottomAppBar")) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 16.dp)
                        .testTag("BottomAppBarRow"),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Bottom Bar",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.testTag("BottomBarText")
                    )
                    IconButton(
                        onClick = { /* TODO */ },
                        modifier = Modifier.testTag("BottomBarIconButton")
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Fav",
                            modifier = Modifier.testTag("BottomBarIcon")
                        )
                    }
                }
            }
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = { /* FAB Action */ },
                modifier = Modifier.testTag("FloatingActionButton")
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.testTag("FloatingActionButtonIcon")
                )
            }
        }, modifier = Modifier.testTag("Scaffold")
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(scrollState)
                    .testTag("MainContentColumn"), verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                Text(
                    "Buttons",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.testTag("ButtonsTitle")
                )
                Button(
                    onClick = { /*TODO*/ }, modifier = Modifier.testTag("FilledButton")
                ) { Text("Filled Button", modifier = Modifier.testTag("FilledButtonText")) }
                OutlinedButton(
                    onClick = { /*TODO*/ }, modifier = Modifier.testTag("OutlinedButton")
                ) { Text("Outlined Button", modifier = Modifier.testTag("OutlinedButtonText")) }
                TextButton(
                    onClick = { /*TODO*/ }, modifier = Modifier.testTag("TextButton")
                ) { Text("Text Button", modifier = Modifier.testTag("TextButtonText")) }
                FilledTonalButton(
                    onClick = { /* Tonal */ }, modifier = Modifier.testTag("FilledTonalButton")
                ) {
                    Text(
                        "Filled Tonal Button", modifier = Modifier.testTag("FilledTonalButtonText")
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier.testTag("IconButton")
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Icon Button",
                        modifier = Modifier.testTag("IconButtonIcon")
                    )
                }
                FloatingActionButton(
                    onClick = { /*TODO*/ }, modifier = Modifier.testTag("SecondaryFAB")
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "FAB",
                        modifier = Modifier.testTag("SecondaryFABIcon")
                    )
                }
                ExtendedFloatingActionButton(
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            Icons.Default.Place,
                            contentDescription = "Extended FAB",
                            modifier = Modifier.testTag("ExtendedFABIcon")
                        )
                    },
                    text = { Text("Extended FAB", modifier = Modifier.testTag("ExtendedFABText")) },
                    modifier = Modifier.testTag("ExtendedFAB")
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .testTag("SpacerAfterButtons")
                )
                Text(
                    "TextFields",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.testTag("TextFieldsTitle")
                )
                TextField(value = text, onValueChange = { text = it }, label = {
                    Text(
                        "Filled TextField", modifier = Modifier.testTag("FilledTextFieldLabel")
                    )
                }, modifier = Modifier.testTag("FilledTextField")
                )
                OutlinedTextField(value = text, onValueChange = { text = it }, label = {
                    Text(
                        "Outlined TextField",
                        modifier = Modifier.testTag("OutlinedTextFieldLabel")
                    )
                }, modifier = Modifier.testTag("OutlinedTextField")
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .testTag("SpacerAfterTextFields")
                )
                Text(
                    "Checkboxes",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.testTag("CheckboxesTitle")
                )
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    modifier = Modifier.testTag("Checkbox")
                )
                TriStateCheckbox(
                    state = androidx.compose.ui.state.ToggleableState.Indeterminate,
                    onClick = { /*TODO*/ },
                    modifier = Modifier.testTag("TriStateCheckbox")
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .testTag("SpacerAfterCheckboxes")
                )
                Text(
                    "Switches",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.testTag("SwitchesTitle")
                )
                var switchOn by remember { mutableStateOf(false) }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.testTag("SwitchRow")
                ) {
                    Switch(
                        checked = switchOn,
                        onCheckedChange = { switchOn = it },
                        modifier = Modifier.testTag("Switch")
                    )
                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                            .testTag("SwitchSpacer")
                    )
                    Text(
                        "Switch is ${if (switchOn) "ON" else "OFF"}",
                        modifier = Modifier.testTag("SwitchStatus")
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                // Chips
                Card(modifier = Modifier.testTag("ChipCard")) {
                    Column(
                        Modifier
                            .padding(16.dp)
                            .testTag("ChipColumn")
                    ) {
                        Text(
                            "Chips",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.testTag("ChipTitle")
                        )

                        // AssistChip
                        AssistChip(onClick = { /* TODO */ }, label = {
                            Text(
                                "Assist Chip", modifier = Modifier.testTag("AssistChipLabel")
                            )
                        }, modifier = Modifier.testTag("AssistChip")
                        )

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .testTag("ChipSpacer1")
                        )

                        // FilterChip
                        var selected by remember { mutableStateOf(false) }
                        FilterChip(
                            selected = selected,
                            onClick = { selected = !selected },
                            label = {
                                Text(
                                    if (selected) "Selected" else "Filter Chip",
                                    modifier = Modifier.testTag("FilterChipLabel")
                                )
                            },
                            modifier = Modifier.testTag("FilterChip")
                        )

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .testTag("ChipSpacer2")
                        )

                        // InputChip
                        var inputChipSelected by remember { mutableStateOf(true) }
                        InputChip(
                            selected = inputChipSelected,
                            onClick = { inputChipSelected = !inputChipSelected },
                            label = {
                                Text(
                                    "Input Chip", modifier = Modifier.testTag("InputChipLabel")
                                )
                            },
                            trailingIcon = {
                                if (inputChipSelected) {
                                    IconButton(
                                        onClick = { inputChipSelected = false },
                                        modifier = Modifier.testTag("InputChipRemoveButton")
                                    ) {
                                        Icon(
                                            Icons.Default.Favorite,
                                            contentDescription = "Remove",
                                            modifier = Modifier.testTag("InputChipRemoveIcon")
                                        )
                                    }
                                }
                            },
                            modifier = Modifier.testTag("InputChip")
                        )

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .testTag("ChipSpacer3")
                        )

                        // SuggestionChip (simulated using AssistChip)
                        AssistChip(onClick = { /* maybe autofill something */ }, label = {
                            Text(
                                "Suggestion Chip",
                                modifier = Modifier.testTag("SuggestionChipLabel")
                            )
                        }, modifier = Modifier.testTag("SuggestionChip")
                        )
                    }
                }


                // Cards
                Card(modifier = Modifier.testTag("ChipCard")) {
                    Column(
                        Modifier
                            .padding(16.dp)
                            .testTag("ChipColumn")
                    ) {
                        Text(
                            "Chips",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.testTag("ChipTitle")
                        )

                        // AssistChip
                        AssistChip(onClick = { /* TODO */ }, label = {
                            Text(
                                "Assist Chip", modifier = Modifier.testTag("AssistChipLabel")
                            )
                        }, modifier = Modifier.testTag("AssistChip")
                        )

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .testTag("ChipSpacer1")
                        )

                        // FilterChip
                        var selected by remember { mutableStateOf(false) }
                        FilterChip(
                            selected = selected,
                            onClick = { selected = !selected },
                            label = {
                                Text(
                                    if (selected) "Selected" else "Filter Chip",
                                    modifier = Modifier.testTag("FilterChipLabel")
                                )
                            },
                            modifier = Modifier.testTag("FilterChip")
                        )

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .testTag("ChipSpacer2")
                        )

                        // InputChip
                        var inputChipSelected by remember { mutableStateOf(true) }
                        InputChip(
                            selected = inputChipSelected,
                            onClick = { inputChipSelected = !inputChipSelected },
                            label = {
                                Text(
                                    "Input Chip", modifier = Modifier.testTag("InputChipLabel")
                                )
                            },
                            trailingIcon = {
                                if (inputChipSelected) {
                                    IconButton(
                                        onClick = { inputChipSelected = false },
                                        modifier = Modifier.testTag("InputChipRemoveButton")
                                    ) {
                                        Icon(
                                            Icons.Default.Favorite,
                                            contentDescription = "Remove",
                                            modifier = Modifier.testTag("InputChipRemoveIcon")
                                        )
                                    }
                                }
                            },
                            modifier = Modifier.testTag("InputChip")
                        )

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .testTag("ChipSpacer3")
                        )

                        // SuggestionChip (simulated using AssistChip)
                        AssistChip(onClick = { /* maybe autofill something */ }, label = {
                            Text(
                                "Suggestion Chip",
                                modifier = Modifier.testTag("SuggestionChipLabel")
                            )
                        }, modifier = Modifier.testTag("SuggestionChip")
                        )
                    }
                }

                Card(modifier = Modifier.testTag("CardsSectionCard")) {
                    Column(
                        Modifier
                            .padding(16.dp)
                            .testTag("CardsSectionColumn")
                    ) {
                        Text(
                            "Cards",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.testTag("CardsSectionTitle")
                        )

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .testTag("CardsSpacer1")
                        )

                        // Filled Card (default)
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .testTag("FilledCard")
                        ) {
                            Column(
                                Modifier
                                    .padding(16.dp)
                                    .testTag("FilledCardColumn")
                            ) {
                                Text(
                                    "Filled Card",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.testTag("FilledCardTitle")
                                )
                                Text(
                                    "This is the default Material3 card.",
                                    modifier = Modifier.testTag("FilledCardDescription")
                                )
                            }
                        }

                        // Elevated Card
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .testTag("ElevatedCard")
                        ) {
                            Column(
                                Modifier
                                    .padding(16.dp)
                                    .testTag("ElevatedCardColumn")
                            ) {
                                Text(
                                    "Elevated Card",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.testTag("ElevatedCardTitle")
                                )
                                Text(
                                    "Elevated cards cast a shadow.",
                                    modifier = Modifier.testTag("ElevatedCardDescription")
                                )
                            }
                        }

                        // Outlined Card
                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .testTag("OutlinedCard")
                        ) {
                            Column(
                                Modifier
                                    .padding(16.dp)
                                    .testTag("OutlinedCardColumn")
                            ) {
                                Text(
                                    "Outlined Card",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.testTag("OutlinedCardTitle")
                                )
                                Text(
                                    "Outlined cards have a border but no elevation.",
                                    modifier = Modifier.testTag("OutlinedCardDescription")
                                )
                            }
                        }
                    }
                }

// Dialogs Section
                Card(modifier = Modifier.testTag("DialogsCard")) {
                    Column(
                        Modifier
                            .padding(16.dp)
                            .testTag("DialogsColumn")
                    ) {
                        Text(
                            "Dialogs",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.testTag("DialogsTitle")
                        )

                        var showAlertDialog by remember { mutableStateOf(false) }
                        var showCustomDialog by remember { mutableStateOf(false) }

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .testTag("DialogsSpacer1")
                        )

                        Button(
                            onClick = { showAlertDialog = true },
                            modifier = Modifier.testTag("ShowAlertDialogButton")
                        ) {
                            Text(
                                "Show AlertDialog",
                                modifier = Modifier.testTag("ShowAlertDialogButtonText")
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .testTag("DialogsSpacer2")
                        )

                        Button(
                            onClick = { showCustomDialog = true },
                            modifier = Modifier.testTag("ShowCustomDialogButton")
                        ) {
                            Text(
                                "Show Custom Dialog",
                                modifier = Modifier.testTag("ShowCustomDialogButtonText")
                            )
                        }

                        // AlertDialog
                        if (showAlertDialog) {
                            AlertDialog(onDismissRequest = { showAlertDialog = false }, title = {
                                Text(
                                    "Alert", modifier = Modifier.testTag("AlertDialogTitle")
                                )
                            }, text = {
                                Text(
                                    "This is a Material3 AlertDialog.",
                                    modifier = Modifier.testTag("AlertDialogText")
                                )
                            }, confirmButton = {
                                TextButton(
                                    onClick = { showAlertDialog = false },
                                    modifier = Modifier.testTag("AlertDialogConfirmButton")
                                ) {
                                    Text(
                                        "OK",
                                        modifier = Modifier.testTag("AlertDialogConfirmText")
                                    )
                                }
                            }, dismissButton = {
                                TextButton(
                                    onClick = { showAlertDialog = false },
                                    modifier = Modifier.testTag("AlertDialogDismissButton")
                                ) {
                                    Text(
                                        "Cancel",
                                        modifier = Modifier.testTag("AlertDialogDismissText")
                                    )
                                }
                            }, modifier = Modifier.testTag("AlertDialog")
                            )
                        }

                        // Custom Dialog
                        if (showCustomDialog) {
                            Dialog(
                                onDismissRequest = { showCustomDialog = false },
                            ) {
                                Surface(
                                    shape = MaterialTheme.shapes.medium,
                                    tonalElevation = 8.dp,
                                    modifier = Modifier.testTag("CustomDialogSurface")
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(24.dp)
                                            .testTag("CustomDialogContent"),
                                        verticalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        Text(
                                            "This is a custom dialog layout.",
                                            modifier = Modifier.testTag("CustomDialogText")
                                        )
                                        Button(
                                            onClick = { showCustomDialog = false },
                                            modifier = Modifier.testTag("CustomDialogCloseButton")
                                        ) {
                                            Text(
                                                "Close",
                                                modifier = Modifier.testTag("CustomDialogCloseText")
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

// Sliders Section
                Text(
                    "Sliders",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.testTag("SlidersTitle")
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .testTag("SlidersColumn"), verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Continuous Slider: ${(sliderPosition * 100).toInt()}",
                        modifier = Modifier.testTag("ContinuousSliderLabel")
                    )
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("ContinuousSlider")
                    )

                    Text(
                        "Discrete Slider (steps = 4)",
                        modifier = Modifier.testTag("DiscreteSliderLabel")
                    )
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        steps = 4,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("DiscreteSlider")
                    )

                    Text(
                        "Range Slider: ${rangeSliderPosition.start.toInt()} - ${rangeSliderPosition.endInclusive.toInt()}",
                        modifier = Modifier.testTag("RangeSliderLabel")
                    )
                    RangeSlider(
                        value = rangeSliderPosition,
                        onValueChange = { rangeSliderPosition = it },
                        valueRange = 0f..100f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .height(56.dp)
                            .testTag("RangeSlider")
                    )
                }

// Snackbar Section
                Card(modifier = Modifier.testTag("SnackbarCard")) {
                    Column(
                        Modifier
                            .padding(16.dp)
                            .testTag("SnackbarColumn")
                    ) {
                        Text(
                            "Snackbar",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.testTag("SnackbarTitle")
                        )
                        Button(onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "This is a snackbar!", actionLabel = "Dismiss"
                                )
                            }
                        }, modifier = Modifier.testTag("ShowSnackbarButton")) {
                            Text("Show Snackbar", modifier = Modifier.testTag("ShowSnackbarText"))
                        }
                    }
                }

// Progress Indicators Section
                Text(
                    "Progress Indicators",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.testTag("ProgressIndicatorsTitle")
                )
                Text("Linear", modifier = Modifier.testTag("LinearLabel"))
                LinearProgressIndicator(
                    progress = sliderPosition.coerceIn(0f, 1f),
                    modifier = Modifier.testTag("LinearProgress")
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .testTag("LinearSpacer")
                )
                Text("Circular", modifier = Modifier.testTag("CircularLabel"))
                CircularProgressIndicator(
                    progress = sliderPosition.coerceIn(0f, 1f),
                    modifier = Modifier.testTag("CircularProgress")
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .testTag("ProgressSpacer")
                )

                Button(
                    onClick = { showSheet.value = true },
                    modifier = Modifier.testTag("ShowBottomSheetButton")
                ) {
                    Text("Show Bottom Sheet", modifier = Modifier.testTag("ShowBottomSheetText"))
                }

                if (showSheet.value) {
                    ModalBottomSheet(
                        onDismissRequest = { showSheet.value = false },
                        sheetState = bottomSheetState,
                        modifier = Modifier.testTag("BottomSheet")
                    ) {
                        Column(
                            Modifier
                                .padding(16.dp)
                                .navigationBarsPadding()
                                .testTag("BottomSheetContent")
                        ) {
                            Text(
                                "This is a bottom sheet!",
                                modifier = Modifier.testTag("BottomSheetLabel")
                            )
                            Button(
                                onClick = { showSheet.value = false },
                                modifier = Modifier.testTag("CloseBottomSheetButton")
                            ) {
                                Text("Close", modifier = Modifier.testTag("CloseBottomSheetText"))
                            }
                        }
                    }
                }

                BadgedBox(
                    badge = {
                        Badge(modifier = Modifier.testTag("Badge")) {
                            Text("3", modifier = Modifier.testTag("BadgeText"))
                        }
                    }, modifier = Modifier.testTag("BadgedBox")
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier.testTag("BadgedIcon")
                    )
                }

                Button(
                    onClick = { showDatePicker = true },
                    modifier = Modifier.testTag("PickDateButton")
                ) {
                    Text("Pick Date", modifier = Modifier.testTag("PickDateText"))
                }

                Text(
                    "Selected: ${selectedDate ?: "None"}",
                    modifier = Modifier.testTag("SelectedDateText")
                )

                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(
                                onClick = { showDatePicker = false },
                                modifier = Modifier.testTag("DatePickerConfirmButton")
                            ) {
                                Text("OK", modifier = Modifier.testTag("DatePickerConfirmText"))
                            }
                        },
                        modifier = Modifier.testTag("DatePickerDialog")
                    ) {
                        DatePicker(
                            state = datePickerState, modifier = Modifier.testTag("DatePicker")
                        )
                    }
                }
            }
        }
    }
}