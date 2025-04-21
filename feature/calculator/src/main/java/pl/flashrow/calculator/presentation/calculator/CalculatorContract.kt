package pl.flashrow.calculator.presentation.calculator

import pl.flashrow.dcc.core.model.ContainerType
import pl.flashrow.dcc.core.model.CoolingPlace
import pl.flashrow.dcc.core.model.DrinkType

internal interface CalculatorContract {
    data class State(
        val drinkTypes: List<DrinkType> = emptyList(),
        val containerTypes: List<ContainerType> = emptyList(),
        val coolingPlaces: List<CoolingPlace> = emptyList(),
        val isLoading: Boolean? = null,
        val selectedDrinkType: DrinkType? = null,
        val selectedContainerType: ContainerType? = null,
    )

    sealed interface Event {
        data object Init : Event
        data class UpdateSelectedDrinkType(val drinkType: DrinkType) : Event
        data class UpdateSelectedContainerType(val containerType: ContainerType) : Event
        data object Calculate : Event
    }

    sealed interface Effect {
        data object NavigateToResult : Effect
    }
}

