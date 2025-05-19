package com.example.demobotapp.automation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

sealed class UiSelector {
    data class Text(val text: String) : UiSelector()
    data class ResourceId(val id: String) : UiSelector()
    data class ContentDescription(val desc: String) : UiSelector()
}

sealed class UiAction {
    object Click : UiAction()
    object LongClick : UiAction()
    data class SetText(val text: String) : UiAction()
}

// Add Context as a constructor parameter
class AutomationController(private val context: Context) {
    private var isRunning = false

    suspend fun launchAndAct(selector: UiSelector, action: UiAction) {
        withTimeout(30_000L) {
            isRunning = true
            try {
                // Launch app
                // Access packageManager through the context
                val packageManager: PackageManager = context.packageManager
                val intent: Intent? = packageManager.getLaunchIntentForPackage("com.google.android.keep")
                // It's good practice to check if the intent is null before starting the activity
                intent?.let {
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Add this flag if launching from a non-Activity context
                    context.startActivity(it)
                }


                // Wait for element
                while (isRunning) {
                    if (findElement(selector)) {
                        performAction(action)
                        break
                    }
                    delay(100)
                }
            } finally {
                isRunning = false
            }
        }
    }

    fun stop() {
        isRunning = false
    }

    // Placeholder for your actual findElement logic
    private fun findElement(selector: UiSelector): Boolean {
        // Implement your element finding logic here
        return true // Replace with actual result
    }

    // Placeholder for your actual performAction logic
    private fun performAction(action: UiAction) {
        // Implement your action performing logic here
    }
}