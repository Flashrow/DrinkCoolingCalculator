package pl.flashrow.calculator.presentation.calculator.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.flashrow.ui.widgets.BaseTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemperatureSlider(onTemperatureUpdate: ((Float) -> Unit)) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var textFieldValue by remember { mutableStateOf(sliderPosition.toString()) }
    val interactionSource = remember { MutableInteractionSource() }
    val colors = SliderDefaults.colors(thumbColor = Color.Red, activeTrackColor = Color.Red)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BaseTextField(
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    sliderPosition = it.toFloatOrNull() ?: sliderPosition
                },
                modifier = Modifier.padding(horizontal = 16.dp).width(80.dp)
            )
            Text(
                text = "°C",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Slider(
            modifier = Modifier
                .semantics { contentDescription = "Localized Description" }
                .padding(horizontal = 16.dp),
            value = sliderPosition,
            onValueChange = {
                sliderPosition = Math.round(it * 2) / 2.0f
                textFieldValue = sliderPosition.toString()
                onTemperatureUpdate(sliderPosition)
            },
            valueRange = 0f..100f,
            interactionSource = interactionSource,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
            thumb = {
                Label(
                    label = {
                        PlainTooltip(
                            modifier = Modifier
                                .requiredSize(55.dp, 45.dp)
                                .wrapContentWidth(),
                            shape = RoundedCornerShape(40.dp),
                            containerColor = Color(0xFF322F35)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = sliderPosition.toString(),
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.background,
                                    modifier = Modifier.align(Alignment.Center),
                                )
                            }
                        }
                    },
                    interactionSource = interactionSource
                ) {
                    SliderDefaults.Thumb(
                        interactionSource = interactionSource,
                        colors = colors
                    )
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TemperatureSliderPreview() {
    TemperatureSlider {}
}