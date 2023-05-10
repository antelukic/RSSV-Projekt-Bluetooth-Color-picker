package com.lukic.rssvprojekt.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorPickerScreen(
    redColorValue: Float,
    blueColorValue: Float,
    greenColorValue: Float,
    onRedColorChange: (Float) -> Unit,
    onBlueColorChange: (Float) -> Unit,
    onGreenColorChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .background(getColorFromRGB(red = redColorValue, blue = blueColorValue, green = greenColorValue))
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .padding(32.dp)
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            ColorSliderWithTitle(label = "Red color value", value = redColorValue, onValueChange = onRedColorChange)

            Spacer(modifier = Modifier.height(16.dp))

            ColorSliderWithTitle(label = "Blue color value", value = blueColorValue, onValueChange = onBlueColorChange)

            Spacer(modifier = Modifier.height(16.dp))

            ColorSliderWithTitle(
                label = "Green color value",
                value = greenColorValue,
                onValueChange = onGreenColorChange
            )
        }
    }
}

@Composable
private fun ColorSliderWithTitle(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = label)

        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..1f
        )
    }
}

@Composable
private fun getColorFromRGB(red: Float, blue: Float, green: Float): Color {
    val hex = Integer.toHexString(android.graphics.Color.rgb(red, green, blue))
    return Color(android.graphics.Color.parseColor("#$hex"))
}
