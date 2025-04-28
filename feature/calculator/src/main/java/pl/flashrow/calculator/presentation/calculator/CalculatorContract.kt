package pl.flashrow.calculator.presentation.calculator

import pl.flashrow.dcc.core.model.BeverageType
import pl.flashrow.dcc.core.model.ContainerType
import pl.flashrow.dcc.core.model.CoolingEnvironment

internal interface CalculatorContract {
    data class State(
        val beverageTypes: List<BeverageType> = emptyList(),
        val containerTypes: List<ContainerType> = emptyList(),
        val coolingEnvironments: List<CoolingEnvironment> = emptyList(),
        val isLoading: Boolean? = null,
        val selectedBeverageType: BeverageType? = null,
        val selectedContainerType: ContainerType? = null,
        val selectedCoolingEnvironment: CoolingEnvironment? = null,
        val beverageStartTemperature: Float? = null,
        val beverageTargetTemperature: Float? = null,
    )

    sealed interface Event {
        data object Init : Event
        data class UpdateSelectedDrinkType(val beverageType: BeverageType) : Event
        data class UpdateSelectedContainerType(val containerType: ContainerType) : Event
        data class UpdateSelectedCoolingEnvironment(val coolingEnvironment: CoolingEnvironment) : Event
        data class UpdateBeverageStartTemperature(val temperature: Float) : Event
        data class UpdateBeverageTargetTemperature(val temperature: Float) : Event
        data object Calculate : Event
    }

    sealed interface Effect {
        data object NavigateToResult : Effect
    }
}

