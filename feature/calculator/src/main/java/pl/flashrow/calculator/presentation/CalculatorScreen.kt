package pl.flashrow.calculator.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Liquor
import androidx.compose.material.icons.outlined.SportsBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import pl.flashrow.calculator.presentation.components.ImageCarousel
import pl.flashrow.calculator.presentation.components.SelectContainerSheet
import pl.flashrow.dcc.core.model.ContainerType
import pl.flashrow.dcc.core.model.DrinkType
import pl.flashrow.dcc.feature.calculator.R
import pl.flashrow.designsystem.Dimens
import pl.flashrow.ui.DccThemedBackground
import pl.flashrow.ui.widgets.BaseLoading
import pl.flashrow.ui.widgets.BaseOutlinedButton

@Composable
fun CalculatorScreen(

) {
    val viewModel: CalculatorViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.onEvent(CalculatorUiEvent.Init)
    }
    val state = viewModel.uiState.collectAsState().value
    if (state.isLoading == false)
        CalculatorContent(state.drinkTypes, state.containerTypes) { viewModel.onEvent(it) }
    else
        BaseLoading()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalculatorContent(
    drinkTypes: List<DrinkType>,
    containerTypes: List<ContainerType>,
    onEvent: (CalculatorUiEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    DccThemedBackground {
        Column(modifier = Modifier.padding(horizontal = Dimens.baseMargin)) {
            Text(
                stringResource(id = R.string.calculate_drink_cooling_time_title),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(Dimens.verticalSectionMargin))
            TitleRow(Icons.Outlined.SportsBar, "Wybierz rodzaj napoju")
            ImageCarousel(drinkTypes, onPageChange = {
                onEvent(CalculatorUiEvent.UpdateSelectedDrinkType(drinkTypes[it]))
            })
            TitleRow(Icons.Outlined.Liquor, "Wybierz typ pojemnika")
            BaseOutlinedButton(
                text = "Wybierz",
                onClick = { showBottomSheet = true }
            )
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                SelectContainerSheet(
                    onSelect = { selectedContainerType ->
                        onEvent(CalculatorUiEvent.UpdateSelectedContainerType(selectedContainerType))
                    },
                    closeModalSheet = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    },
                    containerTypes = containerTypes,
                )
            }
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
            DrinkType(
                resourceId = pl.flashrow.dcc.core.resources.R.drawable.beer_icon,
                name = "Beer",
                alcoholPercentage = 0.04f
            ),
            DrinkType(
                resourceId = pl.flashrow.dcc.core.resources.R.drawable.spirit_icon,
                name = "Spirit",
                alcoholPercentage = 0.40f
            ),
            DrinkType(
                resourceId = pl.flashrow.dcc.core.resources.R.drawable.wine_icon,
                name = "Wine",
                alcoholPercentage = 0.13f
            ),
            DrinkType(
                resourceId = pl.flashrow.dcc.core.resources.R.drawable.tea_icon,
                name = "Tea",
                alcoholPercentage = 0f
            ),
            DrinkType(
                resourceId = pl.flashrow.dcc.core.resources.R.drawable.soft_drink_icon,
                name = "Soft drink",
                alcoholPercentage = 0f
            ),
        ),
        emptyList()
    ) {}
}