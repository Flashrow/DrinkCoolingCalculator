package pl.flashrow.calculator.presentation.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.DeviceThermostat
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Kitchen
import androidx.compose.material.icons.outlined.Liquor
import androidx.compose.material.icons.outlined.SportsBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.flashrow.calculator.presentation.calculator.components.CoolingEnvironmentRadioGroup
import pl.flashrow.calculator.presentation.calculator.components.ImageCarousel
import pl.flashrow.calculator.presentation.calculator.components.SelectContainerSheet
import pl.flashrow.calculator.presentation.calculator.components.TemperatureSlider
import pl.flashrow.dcc.core.enum.CoolingPlaceType
import pl.flashrow.dcc.core.model.BeverageType
import pl.flashrow.dcc.core.model.ContainerType
import pl.flashrow.dcc.core.model.CoolingEnvironment
import pl.flashrow.dcc.feature.calculator.R
import pl.flashrow.designsystem.Dimens
import pl.flashrow.ui.DccThemedBackground
import pl.flashrow.ui.widgets.BaseFilledButton
import pl.flashrow.ui.widgets.BaseLoading
import pl.flashrow.ui.widgets.BaseOutlinedButton

@Composable
fun CalculatorScreen(navigation: CalculatorNavigation) {
    val viewModel: CalculatorViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.onEvent(CalculatorContract.Event.Init)

        viewModel.eventsFlow.collect {
            when (it) {
                is CalculatorContract.Effect.NavigateToResult -> {
                    navigation.navigateToResultsScreen()
                }
            }
        }
    }
    val state = viewModel.uiState.collectAsState().value
    if (state.isLoading == false)
        CalculatorContent(
            beverageTypes = state.beverageTypes,
            containerTypes = state.containerTypes,
            selectedContainerType = state.selectedContainerType,
            coolingEnvironments = state.coolingEnvironments,
        ) {
            viewModel.onEvent(it)
        }
    else
        BaseLoading()
}

@Composable
private fun CalculatorContent(
    beverageTypes: List<BeverageType>,
    containerTypes: List<ContainerType>,
    coolingEnvironments: List<CoolingEnvironment>,
    selectedContainerType: ContainerType? = null,
    onEvent: (CalculatorContract.Event) -> Unit,
) {
    var showSelectContainerSheet by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    DccThemedBackground {
        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.baseMargin)
                .verticalScroll(scrollState)
        ) {
            Text(
                stringResource(id = R.string.calculate_drink_cooling_time_title),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(Dimens.verticalSectionMargin))
            TitleRow(Icons.Outlined.SportsBar, "Wybierz rodzaj napoju")
            ImageCarousel(beverageTypes, onPageChange = {
                onEvent(CalculatorContract.Event.UpdateSelectedDrinkType(beverageTypes[it]))
            })
            TitleRow(Icons.Outlined.Liquor, "Wybierz typ pojemnika")
            BaseOutlinedButton(
                "Wybierz",
                child = if (selectedContainerType != null) {
                    {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                selectedContainerType.name,
                                modifier = Modifier.padding(end = Dimens.smallMargin)
                            )
                            Icon(
                                Icons.Outlined.Edit,
                                contentDescription = "",
                                modifier = Modifier.size(Dimens.iconSize)
                            )
                        }
                    }
                } else null,
                onClick = { showSelectContainerSheet = true }
            )
            TitleRow(Icons.Outlined.DeviceThermostat, "Temperatura początkowa napoju")
            TemperatureSlider(
                onTemperatureUpdate = { temperature ->
                    onEvent(CalculatorContract.Event.UpdateBeverageStartTemperature(temperature))
                }
            )
            TitleRow(Icons.Outlined.Kitchen, "Gdzie schłodzisz napój?")
            CoolingEnvironmentRadioGroup(coolingEnvironments, onChange = { coolingPlace ->
                onEvent(CalculatorContract.Event.UpdateSelectedCoolingEnvironment(coolingPlace))
            })
            Spacer(modifier = Modifier.height(Dimens.verticalSectionMargin))
            TitleRow(Icons.Outlined.AcUnit, "Temperatura docelowa napoju")
            TemperatureSlider(
                onTemperatureUpdate = { temperature ->
                    onEvent(CalculatorContract.Event.UpdateBeverageTargetTemperature(temperature))
                }
            )
            Spacer(modifier = Modifier.height(Dimens.verticalSectionMargin))
            BaseFilledButton("Oblicz", onClick = {
                onEvent(CalculatorContract.Event.Calculate)
            })
            Spacer(modifier = Modifier.height(100.dp))
        }
        if (showSelectContainerSheet) {
            SelectContainerSheet(
                onSelect = { selectedContainerType ->
                    onEvent(CalculatorContract.Event.UpdateSelectedContainerType(selectedContainerType))
                },
                containerTypes = containerTypes,
                onDismiss = { showSelectContainerSheet = false }
            )
        }
    }
}

@Composable
private fun TitleRow(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier.padding(
            top = Dimens.verticalSectionMargin,
            bottom = Dimens.baseMargin
        )
    ) {
        Icon(icon, contentDescription = "")
        Spacer(modifier = Modifier.width(Dimens.smallMargin))
        Text(
            title,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
private fun CalculatorPreview() {
    CalculatorContent(
        listOf(
            BeverageType(
                resourceId = pl.flashrow.dcc.core.resources.R.drawable.beer_icon,
                name = "Beer",
                alcoholPercentage = 0.04f,
                density = 1.01f,
                specificHeat = 4.18f
            ),
            BeverageType(
                resourceId = pl.flashrow.dcc.core.resources.R.drawable.spirit_icon,
                name = "Spirit",
                alcoholPercentage = 0.40f,
                density = 1.01f,
                specificHeat = 4.18f
            ),
            BeverageType(
                resourceId = pl.flashrow.dcc.core.resources.R.drawable.wine_icon,
                name = "Wine",
                alcoholPercentage = 0.13f,
                density = 1.01f,
                specificHeat = 4.18f
            ),
            BeverageType(
                resourceId = pl.flashrow.dcc.core.resources.R.drawable.tea_icon,
                name = "Tea",
                alcoholPercentage = 0f,
                density = 1.01f,
                specificHeat = 4.18f
            ),
            BeverageType(
                resourceId = pl.flashrow.dcc.core.resources.R.drawable.soft_drink_icon,
                name = "Soft drink",
                alcoholPercentage = 0f,
                density = 1.01f,
                specificHeat = 4.18f
            ),
        ),
        emptyList(),
        listOf(
            CoolingEnvironment(CoolingPlaceType.FRIDGE, "Fridge", 4.0, 4.1),
            CoolingEnvironment(CoolingPlaceType.FREEZER, "Freezer", -18.0, 1.2),
            CoolingEnvironment(CoolingPlaceType.CUSTOM, "Inna wartość", 1.0,1.1),
        )
    ) {}
}