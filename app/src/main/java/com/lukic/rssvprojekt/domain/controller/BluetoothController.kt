package com.lukic.rssvprojekt.domain.controller

import com.lukic.rssvprojekt.domain.ConnectionResult
import com.lukic.rssvprojekt.domain.model.BluetoothDevice
import com.lukic.rssvprojekt.domain.model.BluetoothDeviceDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val scannedDevices: StateFlow<List<BluetoothDevice>>
    val pairedDevices: StateFlow<List<BluetoothDevice>>
    val isConnected: StateFlow<Boolean>
    val errors: SharedFlow<String>

    fun startDiscovery()
    fun stopDiscovery()

    fun startBluetoothServer(): Flow<ConnectionResult>

    fun connectToDevice(bluetoothDevice: BluetoothDeviceDomain): Flow<ConnectionResult>

    suspend fun trySendMessage(message: String): String?

    fun closeConnection()

    fun release()
}
