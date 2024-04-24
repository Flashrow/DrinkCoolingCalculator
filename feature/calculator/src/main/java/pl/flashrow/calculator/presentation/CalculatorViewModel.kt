package pl.flashrow.calculator.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.flashrow.dcc.core.model.DrinkType
import pl.flashrow.domain.calculator.GetDrinkTypesUseCase
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val getDrinkTypesUseCase: GetDrinkTypesUseCase
) : ViewModel() {
    private var _uiState = MutableStateFlow(CalculatorUiState.UiState())
    val uiState: StateFlow<CalculatorUiState.UiState> = _uiState

    private lateinit var selectedDrinkType: DrinkType

    fun onEvent(event: CalculatorUiEvent) {
        viewModelScope.launch {
            eventDispatcher(event)
        }
    }

    private fun eventDispatcher(event: CalculatorUiEvent): Any = when (event) {
        CalculatorUiEvent.Init -> init()
        is CalculatorUiEvent.UpdateSelectedDrinkType -> selectDrinkType(event.drinkType)
    }

    private fun init() {
        viewModelScope.launch {
            setLoading(true)
            val drinkTypes = getDrinkTypesUseCase()
            _uiState.update { currentState ->
                currentState.copy(
                    drinkTypes = drinkTypes
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
}