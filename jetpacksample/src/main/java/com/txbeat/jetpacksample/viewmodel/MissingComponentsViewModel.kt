
package com.example.composecatalog.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MissingComponentsViewModel : ViewModel() {

    val selectedTab = mutableStateOf(0)
    val menuExpanded = mutableStateOf(false)
    val selectedRadio = mutableStateOf("Option 1")
    val searchQuery = mutableStateOf("")
    val selectedTime = mutableStateOf("")

    fun selectTab(index: Int) {
        selectedTab.value = index
    }

    fun expandMenu() {
        menuExpanded.value = true
    }

    fun dismissMenu() {
        menuExpanded.value = false
    }

    fun selectRadio(option: String) {
        selectedRadio.value = option
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun updateTime(time: String) {
        selectedTime.value = time
    }
    val selectedDialOption = mutableStateOf("1")
    val isDialMenuExpanded = mutableStateOf(false)
    val inputTime = mutableStateOf("")

    fun updateDialOption(option: String) {
        selectedDialOption.value = option
        isDialMenuExpanded.value = false
    }

    fun toggleDialMenu() {
        isDialMenuExpanded.value = !isDialMenuExpanded.value
    }

    fun updateInputTime(time: String) {
        inputTime.value = time
    }

    val selectedTextOnlyTab = mutableStateOf(0)
    fun selectTextOnlyTab(index: Int) {
        selectedTextOnlyTab.value = index
    }
}
