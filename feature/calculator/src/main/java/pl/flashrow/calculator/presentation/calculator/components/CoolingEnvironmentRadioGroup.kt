package pl.flashrow.calculator.presentation.calculator.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.flashrow.dcc.core.enum.CoolingPlaceType
import pl.flashrow.dcc.core.model.CoolingEnvironment
import pl.flashrow.designsystem.Dimens
import pl.flashrow.ui.widgets.BaseTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoolingEnvironmentRadioGroup(coolingEnvironments: List<CoolingEnvironment>, onChange: (CoolingEnvironment) -> Unit) {
    val scope = rememberCoroutineScope()
    var selectedCoolingPlace by remember { mutableStateOf<CoolingPlaceType?>(null) }
    val sheetState = rememberModalBottomSheetState()
    var customValueText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = Dimens.baseMargin)
    ) {
        Spacer(modifier = Modifier.padding(top = Dimens.smallMargin))
        coolingEnvironments.forEach { coolingEnvironment ->
            ContainerTypeRow(
                name = coolingEnvironment.name,
                temperature = coolingEnvironment.temperature,
                isSelected = selectedCoolingPlace == coolingEnvironment.coolingPlaceType,
                onClick = {
                    selectedCoolingPlace = coolingEnvironment.coolingPlaceType
                    onChange(coolingEnvironment)
                }
            )
        }
        Box(modifier = Modifier.clip(RoundedCornerShape(20.dp))) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedCoolingPlace = CoolingPlaceType.CUSTOM
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selectedCoolingPlace == CoolingPlaceType.CUSTOM,
                    onClick = {
                        selectedCoolingPlace = CoolingPlaceType.CUSTOM
                    }
                )
                Text(
                    text = "Inna wartość:",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = Dimens.baseMargin)
                )
                BaseTextField(
                    value = customValueText,
                    onValueChange = { customValueText = it },
                    modifier = Modifier.width(70.dp)
                )
                Text(
                    text = "°C",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = Dimens.baseMargin)
                )
            }
        }
    }
}

@Composable
private fun ContainerTypeRow(
    name: String,
    temperature: Double,
    isSelected: Boolean,
    onClick: (Double?) -> Unit
) {
    Box(modifier = Modifier.clip(RoundedCornerShape(20.dp))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(temperature)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = isSelected,
                onClick = {
                    onClick(temperature)
                }
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = Dimens.baseMargin)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoolingPlaceRadioGroupPreview() {
    val sampleCoolingEnvironments = listOf(
        CoolingEnvironment(name = "Fridge", temperature = 4.0, coolingPlaceType = CoolingPlaceType.FRIDGE, convectionCoefficient = 4.1),
        CoolingEnvironment(name = "Freezer", temperature = -18.9, coolingPlaceType = CoolingPlaceType.FREEZER, convectionCoefficient = 4.1),
        CoolingEnvironment(name = "Room Temperature", temperature = 22.1, coolingPlaceType = CoolingPlaceType.CUSTOM, convectionCoefficient = 4.1),
    )
    CoolingEnvironmentRadioGroup(coolingEnvironments = sampleCoolingEnvironments, {})
}