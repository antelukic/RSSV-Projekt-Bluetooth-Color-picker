package com.lukic.rssvprojekt

import com.lukic.rssvprojekt.data.AndroidBluetoothController
import com.lukic.rssvprojekt.domain.controller.BluetoothController
import com.lukic.rssvprojekt.presentation.BluetoothViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<BluetoothController> { AndroidBluetoothController(androidContext()) }

    viewModel { BluetoothViewModel(get()) }
}
