package pl.flashrow.calculator.presentation.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.flashrow.dcc.core.model.BeverageType
import pl.flashrow.dcc.core.model.ContainerType
import pl.flashrow.dcc.core.model.CoolingEnvironment
import pl.flashrow.domain.calculator.CalculateCoolingTimeUseCase
import pl.flashrow.domain.calculator.GetContainerTypesUseCase
import pl.flashrow.domain.calculator.GetCoolingPlaceTypesUseCase
import pl.flashrow.domain.calculator.GetDrinkTypesUseCase
import javax.inject.Inject

@HiltViewModel
internal class CalculatorViewModel @Inject constructor(
    private val getDrinkTypesUseCase: GetDrinkTypesUseCase,
    private val getContainerTypesUseCase: GetContainerTypesUseCase,
    private val getCoolingPlaceTypesUseCase: GetCoolingPlaceTypesUseCase,
    private val calculateCoolingTimeUseCase: CalculateCoolingTimeUseCase,
) : ViewModel() {
    private var _uiState = MutableStateFlow(CalculatorContract.State())
    val uiState: StateFlow<CalculatorContract.State> = _uiState

    private val eventChannel = Channel<CalculatorContract.Effect>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    private lateinit var selectedBeverageType: BeverageType
    private lateinit var selectedContainerType: ContainerType

    fun onEvent(event: CalculatorContract.Event) {
        viewModelScope.launch {
            eventDispatcher(event)
        }
    }

    private suspend fun eventDispatcher(event: CalculatorContract.Event): Any = when (event) {
        CalculatorContract.Event.Init -> init()
        is CalculatorContract.Event.UpdateSelectedDrinkType -> selectBeverageType(event.beverageType)
        is CalculatorContract.Event.UpdateSelectedContainerType -> selectContainerType(event.containerType)
        is CalculatorContract.Event.UpdateSelectedCoolingEnvironment -> selectCoolingEnvironment(event.coolingEnvironment)
        is CalculatorContract.Event.UpdateBeverageStartTemperature -> setBeverageStartTemperature(event.temperature)
        is CalculatorContract.Event.UpdateBeverageTargetTemperature -> setBeverageTargetTemperature(event.temperature)
        CalculatorContract.Event.Calculate -> { calculateCoolingTime() }
    }

    private fun init() {
        viewModelScope.launch {
            setLoading(true)
            val drinkTypes = getDrinkTypesUseCase()
            val containerTypes = getContainerTypesUseCase()
            val coolingPlaceTypes = getCoolingPlaceTypesUseCase()
            _uiState.update { currentState ->
                currentState.copy(
                    beverageTypes = drinkTypes,
                    containerTypes = containerTypes,
                    coolingEnvironments = coolingPlaceTypes,
                )
            }
            selectBeverageType(drinkTypes.first())
            setLoading(false)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = isLoading
            )
        }
    }

    private fun selectBeverageType(beverageType: BeverageType) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedBeverageType = beverageType,
            )
        }
        Log.d("CalculatorViewModel", "Selected drink type: $beverageType")
    }

    private fun selectContainerType(containerType: ContainerType) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedContainerType = containerType,
            )
        }
        Log.d("CalculatorViewModel", "Selected container type: $containerType")
    }

    private fun selectCoolingEnvironment(coolingEnvironment: CoolingEnvironment) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCoolingEnvironment = coolingEnvironment,
            )
        }
        Log.d("CalculatorViewModel", "Selected cooling environment: $coolingEnvironment")
    }

    private fun setBeverageStartTemperature(temperature: Float) {
        _uiState.update { currentState ->
            currentState.copy(
                beverageStartTemperature = temperature,
            )
        }
    }

    private fun setBeverageTargetTemperature(temperature: Float) {
        _uiState.update { currentState ->
            currentState.copy(
                beverageTargetTemperature = temperature,
            )
        }
    }

    private suspend fun calculateCoolingTime() {
        if(_uiState.value.selectedBeverageType == null ||
            _uiState.value.selectedContainerType == null ||
            _uiState.value.selectedCoolingEnvironment == null ||
            _uiState.value.beverageStartTemperature == null ||
            _uiState.value.beverageTargetTemperature == null
        ) {
            Log.e("CalculatorViewModel", "Missing required parameters for calculation, parameters: " +
                    "selected Beverage type: ${_uiState.value.selectedBeverageType}, " +
                    "selected Container type: ${_uiState.value.selectedContainerType}, " +
                    "selected Cooling environment: ${_uiState.value.selectedCoolingEnvironment}, " +
                    "beverage start temperature: ${_uiState.value.beverageStartTemperature}," +
                    " beverage target temperature: ${_uiState.value.beverageTargetTemperature}")
            return
        }

        val result = calculateCoolingTimeUseCase(
            beverageType = _uiState.value.selectedBeverageType!!,
            container = _uiState.value.selectedContainerType!!,
            coolingEnvironment = _uiState.value.selectedCoolingEnvironment!!,
            drinkStartTemperature = _uiState.value.beverageStartTemperature!!,
            drinkTargetTemperature = _uiState.value.beverageTargetTemperature!!,
        )
        if(result.isSuccess){
            Log.d("CalculatorViewModel", "Calculated cooling time: ${result.getOrNull()}")
            eventChannel.send(CalculatorContract.Effect.NavigateToResult)
        }
        else {
            Log.e("CalculatorViewModel", "Error calculating cooling time: ${result.exceptionOrNull()}")
        }
    }
}