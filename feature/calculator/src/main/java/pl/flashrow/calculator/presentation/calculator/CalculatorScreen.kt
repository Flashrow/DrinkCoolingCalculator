package pl.flashrow.calculator.presentation.calculator

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pl.flashrow.calculator.presentation.calculator.components.CoolingEnvironmentRadioGroup
import pl.flashrow.calculator.presentation.calculator.components.ImageCarousel
import pl.flashrow.calculator.presentation.calculator.components.SelectContainerSheet
import pl.flashrow.calculator.presentation.calculator.components.TemperatureSlider
import pl.flashrow.core.ui.LottieAnimationPlayer
import pl.flashrow.dcc.core.enum.CoolingPlaceType
import pl.flashrow.dcc.core.model.BeverageType
import pl.flashrow.dcc.core.model.CoolingEnvironment
import pl.flashrow.dcc.core.resources.R
import pl.flashrow.designsystem.Dimens
import pl.flashrow.ui.DccThemedBackground
import pl.flashrow.ui.widgets.BaseFilledButton
import pl.flashrow.ui.widgets.BaseLoading
import pl.flashrow.ui.widgets.BaseOutlinedButton
import pl.flashrow.ui.widgets.ErrorBox

@Composable
fun CalculatorScreen(navigation: CalculatorNavigation) {
    val viewModel: CalculatorViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    val state = viewModel.uiState.collectAsState().value
    val scope = rememberCoroutineScope()
    var showAnimation by remember { mutableStateOf(false) }
    var onAnimationEndAction by remember { mutableStateOf<(() -> Unit)?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val temperatureExceededErrorMessage = stringResource(R.string.error_target_temperature_higher_than_initial)
    val coolingEnvironmentTemperatureLowerThanTargetErrorMessage = stringResource(R.string.error_cooling_place_temperature_higher_than_target)

    fun playCelebrationAnimation(onEnd: (() -> Unit)? = null) {
        onAnimationEndAction = onEnd
        showAnimation = true
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(CalculatorContract.Event.Init)
        viewModel.eventsFlow.collect {
            when (it) {
                is CalculatorContract.Effect.NavigateToResult -> {
                    playCelebrationAnimation(onEnd = {
                        CoroutineScope(this.coroutineContext).launch {
                            scrollState.animateScrollTo(0)
                        }
                        navigation.navigateToResultsScreen(it.coolingTime)
                    })
                }

                is CalculatorContract.Effect.ErrorSnackbar -> {
                    showAnimation = false
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = when(it) {
                                is CalculatorContract.Effect.TargetTemperatureExceeded -> temperatureExceededErrorMessage
                                else -> coolingEnvironmentTemperatureLowerThanTargetErrorMessage
                            }
                        )
                    }
                }
            }
        }
    }

    if (state.isLoading == false)
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { _ ->
        CalculatorContent(
            state = state,
            onEvent = viewModel::onEvent,
            scrollState = scrollState,
        )
    }
    else
        BaseLoading()

    if (showAnimation) {
        LottieAnimationPlayer(
            animationResId = R.raw.lottie_snowfall,
            isPlaying = true,
            restartOnPlay = true,
            iterations = 1,
            speed = 20f,
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.Crop,
            onAnimationEnd = { onAnimationEndAction?.invoke() }
        )
    }
}

@Composable
private fun CalculatorContent(
    state: CalculatorContract.State,
    onEvent: (CalculatorContract.Event) -> Unit,
    scrollState: ScrollState,
) {
    var showSelectContainerSheet by remember { mutableStateOf(false) }
    DccThemedBackground {
        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.baseMargin)
                .verticalScroll(scrollState)
        ) {
            Text(
                stringResource(id = R.string.calculate_drink_cooling_time_title),
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(Dimens.verticalSectionMargin))
            TitleRow(Icons.Outlined.SportsBar, stringResource(id = R.string.select_beverage_type))
            ImageCarousel(state.beverageTypes, onPageChange = {
                onEvent(CalculatorContract.Event.UpdateSelectedDrinkType(state.beverageTypes[it]))
            })
            ErrorBox(
                isError = state.containerTypeNotSelectedValidator,
            ) {
                Column {
                    TitleRow(
                        Icons.Outlined.Liquor,
                        stringResource(id = R.string.select_container_type),
                        modifier = Modifier.padding(bottom = Dimens.baseMargin)
                    )
                    BaseOutlinedButton(
                        text = stringResource(id = R.string.select),
                        child = if (state.selectedContainerType != null) {
                            {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        stringResource(state.selectedContainerType.nameResourceId),
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
                        onClick = { showSelectContainerSheet = true },
                        isError = state.containerTypeNotSelectedValidator
                    )
                    if (state.containerTypeNotSelectedValidator)
                        Text(
                            stringResource(R.string.error_no_container_selected),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = Dimens.smallMargin)
                        )
                }
            }
            TitleRow(
                Icons.Outlined.DeviceThermostat,
                stringResource(id = R.string.initial_drink_temperature)
            )
            TemperatureSlider(
                onTemperatureUpdate = { temperature ->
                    onEvent(CalculatorContract.Event.UpdateBeverageStartTemperature(temperature))
                },
                selectedTemperature = state.beverageStartTemperature
            )
            ErrorBox(
                isError = state.coolingEnvironmentNotSelectedValidator
            ) {
                Column {
                    TitleRow(
                        Icons.Outlined.Kitchen,
                        stringResource(id = R.string.select_cooling_place_title),
                        modifier = Modifier.padding(bottom = Dimens.baseMargin)
                    )
                    CoolingEnvironmentRadioGroup(
                        state.coolingEnvironments,
                        onChange = { coolingPlace ->
                            onEvent(
                                CalculatorContract.Event.UpdateSelectedCoolingEnvironment(
                                    coolingPlace
                                )
                            )
                        },
                        selectedCoolingEnvironment = state.selectedCoolingEnvironment
                    )
                    if (state.containerTypeNotSelectedValidator)
                        Text(
                            stringResource(R.string.error_no_cooling_place_selected),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = Dimens.smallMargin)
                        )
                }
            }
            Spacer(modifier = Modifier.height(Dimens.verticalSectionMargin))
            TitleRow(
                Icons.Outlined.AcUnit,
                stringResource(id = R.string.select_target_temperature_title)
            )
            TemperatureSlider(
                onTemperatureUpdate = { temperature ->
                    onEvent(CalculatorContract.Event.UpdateBeverageTargetTemperature(temperature))
                },
                selectedTemperature = state.beverageTargetTemperature
            )
            Spacer(modifier = Modifier.height(Dimens.verticalSectionMargin))
            BaseFilledButton(stringResource(R.string.calculate), onClick = {
                onEvent(CalculatorContract.Event.Calculate)
            })
            Spacer(modifier = Modifier.height(100.dp))
        }
        if (showSelectContainerSheet) {
            SelectContainerSheet(
                onSelect = { selectedContainerType ->
                    onEvent(
                        CalculatorContract.Event.UpdateSelectedContainerType(
                            selectedContainerType
                        )
                    )
                },
                containerTypes = state.containerTypes,
                onDismiss = { showSelectContainerSheet = false }
            )
        }
    }
}

@Composable
private fun TitleRow(icon: ImageVector, title: String, modifier: Modifier? = null) {
    Row(
        modifier = modifier ?: Modifier.padding(
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
        scrollState = rememberScrollState(),
        onEvent = {},
        state = CalculatorContract.State(
            listOf(
                BeverageType(
                    iconResourceId = R.drawable.beer_icon,
                    nameResourceId = R.string.beer,
                    alcoholPercentage = 0.04f,
                    density = 1.01f,
                    specificHeat = 4.18f
                ),
                BeverageType(
                    iconResourceId = R.drawable.spirit_icon,
                    nameResourceId = R.string.strong_alcohol,
                    alcoholPercentage = 0.40f,
                    density = 1.01f,
                    specificHeat = 4.18f
                ),
                BeverageType(
                    iconResourceId = R.drawable.wine_icon,
                    nameResourceId = R.string.wine,
                    alcoholPercentage = 0.13f,
                    density = 1.01f,
                    specificHeat = 4.18f
                ),
                BeverageType(
                    iconResourceId = R.drawable.tea_icon,
                    nameResourceId = R.string.tea,
                    alcoholPercentage = 0f,
                    density = 1.01f,
                    specificHeat = 4.18f
                ),
                BeverageType(
                    iconResourceId = R.drawable.soft_drink_icon,
                    nameResourceId = R.string.soft_drink,
                    alcoholPercentage = 0f,
                    density = 1.01f,
                    specificHeat = 4.18f
                ),
            ),
            emptyList(),
            listOf(
                CoolingEnvironment(CoolingPlaceType.FRIDGE, R.string.fridge, 4.0, 4.1),
                CoolingEnvironment(CoolingPlaceType.FREEZER, R.string.fridge, -18.0, 1.2),
                CoolingEnvironment(CoolingPlaceType.CUSTOM, R.string.custom_value, 1.0, 1.1),
            )
        )
    )
}