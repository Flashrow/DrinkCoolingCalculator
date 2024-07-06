package pl.flashrow.calculator.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemperatureSlider(
) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val interactionSource = remember { MutableInteractionSource() }
    val colors = SliderDefaults.colors(thumbColor = Color.Red, activeTrackColor = Color.Red)
    Slider(
        modifier = Modifier.semantics { contentDescription = "Localized Description" }.padding(horizontal = 16.dp),
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
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
                        Box (
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            Text(
                                "%.1f".format(Math.round(sliderPosition * 2) / 2.0f),
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

@Composable
@Preview
private fun TemperatureSliderPreview() {
    TemperatureSlider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun ThumbPreview() {
    Label(
        label = {
            PlainTooltip(
                modifier = Modifier
                    .requiredSize(45.dp, 25.dp)
                    .wrapContentWidth()
            ) {
                Text("%.1f".format(Math.round(5.0 * 2) / 2.0f))
            }
        },
    ) {}
}