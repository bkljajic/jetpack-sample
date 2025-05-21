
package com.example.composecatalog.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text

    private val _checked = MutableStateFlow(false)
    val checked: StateFlow<Boolean> = _checked

    private val _sliderValue = MutableStateFlow(0.5f)
    val sliderValue: StateFlow<Float> = _sliderValue

    fun onTextChange(newText: String) {
        _text.value = newText
    }

    fun onCheckedChange(newChecked: Boolean) {
        _checked.value = newChecked
    }

    fun onSliderChange(newValue: Float) {
        _sliderValue.value = newValue
    }
}
