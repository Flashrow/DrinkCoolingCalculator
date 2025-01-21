package pl.flashrow.calculator.presentation.components

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
import pl.flashrow.dcc.core.model.CoolingPlace
import pl.flashrow.designsystem.Dimens
import pl.flashrow.ui.widgets.BaseTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoolingPlaceRadioGroup(coolingPlaces: List<CoolingPlace>) {
    val scope = rememberCoroutineScope()
    var selectedCoolingPlace by remember { mutableStateOf<CoolingPlaceType?>(null) }
    val sheetState = rememberModalBottomSheetState()
    var customValueText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = Dimens.baseMargin)
    ) {
        Spacer(modifier = Modifier.padding(top = Dimens.smallMargin))
        coolingPlaces.forEach { coolingPlace ->
            ContainerTypeRow(
                name = coolingPlace.name,
                temperature = coolingPlace.temperature,
                isSelected = selectedCoolingPlace == coolingPlace.coolingPlaceType,
                onClick = {
                    selectedCoolingPlace = coolingPlace.coolingPlaceType
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
    temperature: Int?,
    isSelected: Boolean,
    onClick: (Int?) -> Unit
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
    val sampleCoolingPlaces = listOf(
        CoolingPlace(name = "Fridge", temperature = 4, coolingPlaceType = CoolingPlaceType.FRIDGE),
        CoolingPlace(name = "Freezer", temperature = -18, coolingPlaceType = CoolingPlaceType.FREEZER),
        CoolingPlace(name = "Room Temperature", temperature = 22, coolingPlaceType = CoolingPlaceType.CUSTOM),
    )
    CoolingPlaceRadioGroup(coolingPlaces = sampleCoolingPlaces)
}