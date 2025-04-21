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
import pl.flashrow.dcc.core.model.ContainerType
import pl.flashrow.dcc.core.model.DrinkType
import pl.flashrow.domain.calculator.GetContainerTypesUseCase
import pl.flashrow.domain.calculator.GetCoolingPlaceTypesUseCase
import pl.flashrow.domain.calculator.GetDrinkTypesUseCase
import javax.inject.Inject

@HiltViewModel
internal class CalculatorViewModel @Inject constructor(
    private val getDrinkTypesUseCase: GetDrinkTypesUseCase,
    private val getContainerTypesUseCase: GetContainerTypesUseCase,
    private val getCoolingPlaceTypesUseCase: GetCoolingPlaceTypesUseCase,
) : ViewModel() {
    private var _uiState = MutableStateFlow(CalculatorContract.State())
    val uiState: StateFlow<CalculatorContract.State> = _uiState

    private val eventChannel = Channel<CalculatorContract.Effect>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    private lateinit var selectedDrinkType: DrinkType
    private lateinit var selectedContainerType: ContainerType

    fun onEvent(event: CalculatorContract.Event) {
        viewModelScope.launch {
            eventDispatcher(event)
        }
    }

    private suspend fun eventDispatcher(event: CalculatorContract.Event): Any = when (event) {
        CalculatorContract.Event.Init -> init()
        is CalculatorContract.Event.UpdateSelectedDrinkType -> selectDrinkType(event.drinkType)
        is CalculatorContract.Event.UpdateSelectedContainerType -> selectContainerType(event.containerType)
        CalculatorContract.Event.Calculate -> {
            eventChannel.send(CalculatorContract.Effect.NavigateToResult)
        }
    }

    private fun init() {
        viewModelScope.launch {
            setLoading(true)
            val drinkTypes = getDrinkTypesUseCase()
            val containerTypes = getContainerTypesUseCase()
            val coolingPlaceTypes = getCoolingPlaceTypesUseCase()
            _uiState.update { currentState ->
                currentState.copy(
                    drinkTypes = drinkTypes,
                    containerTypes = containerTypes,
                    coolingPlaces = coolingPlaceTypes,
                )
            }
            selectDrinkType(drinkTypes.first())
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

    private fun selectDrinkType(drinkType: DrinkType) {
        selectedDrinkType = drinkType
        Log.d("CalculatorViewModel", "Selected drink type: $drinkType")
    }

    private fun selectContainerType(containerType: ContainerType) {
        selectedContainerType = containerType
        _uiState.update { currentState ->
            currentState.copy(
                selectedContainerType = selectedContainerType,
            )
        }
        Log.d("CalculatorViewModel", "Selected container type: $containerType")
    }
}