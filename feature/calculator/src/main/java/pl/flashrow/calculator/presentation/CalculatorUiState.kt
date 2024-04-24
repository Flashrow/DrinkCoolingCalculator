package pl.flashrow.calculator.presentation

import pl.flashrow.dcc.core.model.DrinkType

sealed interface CalculatorUiState {
    data class UiState (
        val drinkTypes: List<DrinkType> = emptyList(),
        val isLoading: Boolean? = null,
    )
}

sealed interface CalculatorUiEvent {
    data object  Init: CalculatorUiEvent
    data class UpdateSelectedDrinkType(val drinkType: DrinkType):CalculatorUiEvent
}