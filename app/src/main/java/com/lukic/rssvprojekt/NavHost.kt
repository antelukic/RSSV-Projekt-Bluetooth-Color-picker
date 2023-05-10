package com.lukic.rssvprojekt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lukic.rssvprojekt.presentation.BluetoothViewModel
import com.lukic.rssvprojekt.presentation.components.ColorPickerScreen
import com.lukic.rssvprojekt.presentation.components.DeviceScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyNavHost(
    navController: NavHostController,
    startDestination: String = "scan"
) {
    val viewModel: BluetoothViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("scan") {
            DeviceScreen(
                state = viewModel.state.collectAsState().value,
                onStartScan = viewModel::startScan,
                onStopScan = viewModel::stopScan,
                onDeviceClick = viewModel::connectToDevice,
            )
        }
        composable("colorPicker") {
            ColorPickerScreen(onColorChange = viewModel::sendMessage)
        }
    }
}
