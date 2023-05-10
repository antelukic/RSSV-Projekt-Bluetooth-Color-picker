package com.lukic.rssvprojekt.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun ColorPickerScreen(
    onColorChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val controller = rememberColorPickerController()
    controller.setDebounceDuration(500.milliseconds.inWholeMilliseconds)

    Box(modifier = modifier) {
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(10.dp)
                .align(Alignment.Center),
            controller = controller,
            onColorChanged = { colorEnvelope: ColorEnvelope ->
                onColorChange(
                    colorEnvelope.getRGB()
                )
            }
        )
    }
}

private fun ColorEnvelope.getRGB(): String {
    var colorToSend = ""
    if(color.red >= 0.50) colorToSend += "R"
    if(color.green >= 0.50) colorToSend += "G"
    if(color.blue >= 0.50) colorToSend += "B"
    return colorToSend
}
