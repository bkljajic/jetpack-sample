
package com.txbeat.jetpacksample.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExtraComponentsViewModel : ViewModel() {
    private val _dialogShown = MutableStateFlow(false)
    val dialogShown: StateFlow<Boolean> = _dialogShown

    fun showDialog() {
        _dialogShown.value = true
    }

    fun dismissDialog() {
        _dialogShown.value = false
    }
}
