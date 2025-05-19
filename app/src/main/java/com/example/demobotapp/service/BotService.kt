package com.example.demobotapp.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import timber.log.Timber

class BotService : AccessibilityService() {
    
    override fun onServiceConnected() {
        Timber.d("Bot service connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        // Handle events
    }

    override fun onInterrupt() {
        Timber.w("Bot service interrupted")
    }

    companion object {
        private var instance: BotService? = null
        
        fun getInstance(): BotService? = instance
    }
}
