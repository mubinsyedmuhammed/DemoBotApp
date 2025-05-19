package com.example.demobotapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MainUiState(
    val isStarted: Boolean = false
)

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun onStartClick() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isStarted = true)
        }
    }

    fun onStopClick() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isStarted = false)
        }
    }
}
