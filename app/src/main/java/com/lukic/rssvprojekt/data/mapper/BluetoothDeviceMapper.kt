package com.lukic.rssvprojekt.data.mapper

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.lukic.rssvprojekt.domain.model.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain() =
    BluetoothDeviceDomain(
        name = name,
        address = address
    )
