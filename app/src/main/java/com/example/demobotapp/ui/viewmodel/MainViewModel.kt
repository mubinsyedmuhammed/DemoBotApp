package com.example.demobotapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MainUiState(
    val isRunning: Boolean = false,
    val isRefreshing: Boolean = false
)

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun onStartClick() {
        _uiState.value = _uiState.value.copy(isRunning = true)
    }

    fun onStopClick() {
        _uiState.value = _uiState.value.copy(isRunning = false)
    }

    fun onRefresh() {
        _uiState.value = _uiState.value.copy(isRefreshing = true)
        // Simulate refresh
        _uiState.value = MainUiState(
            isRunning = false,
            isRefreshing = false
        )
    }
}
