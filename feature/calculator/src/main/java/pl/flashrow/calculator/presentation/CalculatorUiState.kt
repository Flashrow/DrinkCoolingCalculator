package pl.flashrow.calculator.presentation

import pl.flashrow.dcc.core.model.DrinkType

sealed interface CalculatorUiState {
    data class State (
        val drinkTypes: List<DrinkType> = emptyList()
    )
    sealed interface Event {
        data object  Init:Event
    }
}