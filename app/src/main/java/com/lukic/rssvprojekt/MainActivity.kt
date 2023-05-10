package com.lukic.rssvprojekt

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.lukic.rssvprojekt.presentation.BluetoothViewModel
import com.lukic.rssvprojekt.ui.theme.RSSVProjektTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private val bluetoothManager by lazy {
        applicationContext.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { /* Not needed */ }

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { perms ->
            val canEnableBluetooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                perms[Manifest.permission.BLUETOOTH_CONNECT] == true
            } else true

            if (canEnableBluetooth && !isBluetoothEnabled) {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                )
            )
        }

        setContent {
            RSSVProjektTheme {
                val viewModel = koinViewModel<BluetoothViewModel>()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = state.errorMessage) {
                    state.errorMessage?.let { message ->
                        Toast.makeText(
                            applicationContext,
                            message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                LaunchedEffect(key1 = state.isConnecting) {
                    Toast.makeText(
                        applicationContext,
                        "You are connecting",
                        Toast.LENGTH_LONG
                    ).show()
                }

                LaunchedEffect(key1 = state.isConnected) {
                    Toast.makeText(
                        applicationContext,
                        "You are connected",
                        Toast.LENGTH_LONG
                    ).show()
                }

                val navController = rememberNavController()
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    BackHandler {
                        viewModel.disconnectFromDevice()
                    }
                    MyNavHost(navController = navController)
                    when {
                        state.isConnected -> {
                            navController.navigate("colorPicker")
                            viewModel.waitForIncomingConnections()
                        }
//
//                        else -> {
//                            navController.navigate("scan")
//                        }
                    }
                }
            }
        }
    }
}
