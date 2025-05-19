package com.example.demobotapp.ui.viewmodel

import android.app.Application // Import Application
import androidx.lifecycle.AndroidViewModel // Change to AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.demobotapp.automation.AutomationController
import com.example.demobotapp.automation.UiAction
import com.example.demobotapp.automation.UiSelector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MainUiState(
    val isRunning: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)

// Change ViewModel to AndroidViewModel and pass Application to the constructor
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    // Pass the application context to AutomationController
    private val automationController = AutomationController(application.applicationContext)

    fun onStartClick() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                _uiState.value = _uiState.value.copy(isRunning = true, error = null)
                automationController.launchAndAct(
                    UiSelector.Text("Search your notes"),
                    UiAction.Click
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }

    fun onStopClick() {
        viewModelScope.launch {
            automationController.stop()
            _uiState.value = _uiState.value.copy(isRunning = false)
        }
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