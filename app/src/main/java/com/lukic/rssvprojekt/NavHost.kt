package com.lukic.rssvprojekt

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
    startDestination: String = "colorPicker"
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
            val state = viewModel.state.collectAsState().value
            ColorPickerScreen(
                redColorValue = state.redColorValue,
                blueColorValue = state.blueColorValue,
                greenColorValue = state.greenColorValue,
                onRedColorChange = viewModel::updateRedColor,
                onBlueColorChange = viewModel::updateBlueColor,
                onGreenColorChange = viewModel::updateGreenColor,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
