package com.lukic.rssvprojekt.presentation.model

import com.lukic.rssvprojekt.domain.model.BluetoothDeviceDomain

data class BluetoothUIState(
    val scannedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val pairedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val errorMessage: String? = null,
    val redColorValue: Float = 0f,
    val blueColorValue: Float = 0f,
    val greenColorValue: Float = 0f
)
