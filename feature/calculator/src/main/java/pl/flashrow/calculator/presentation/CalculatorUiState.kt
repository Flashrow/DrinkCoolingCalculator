package pl.flashrow.calculator.presentation

import pl.flashrow.dcc.core.model.ContainerType
import pl.flashrow.dcc.core.model.DrinkType

sealed interface CalculatorUiState {
    data class UiState (
        val drinkTypes: List<DrinkType> = emptyList(),
        val containerTypes: List<ContainerType> = emptyList(),
        val isLoading: Boolean? = null,
        val selectedDrinkType: DrinkType? = null,
        val selectedContainerType: ContainerType? = null,
    )
}

sealed interface CalculatorUiEvent {
    data object  Init: CalculatorUiEvent
    data class UpdateSelectedDrinkType(val drinkType: DrinkType):CalculatorUiEvent
    data class UpdateSelectedContainerType(val containerType: ContainerType):CalculatorUiEvent
}