package pl.flashrow.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.flashrow.domain.calculator.GetDrinkTypesUseCase
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val getDrinkTypesUseCase: GetDrinkTypesUseCase
) : ViewModel() {
    private var _uiState = MutableStateFlow(CalculatorUiState.State())
    val uiState: StateFlow<CalculatorUiState.State> = _uiState

    fun onEvent(event: CalculatorUiState.Event) {
        viewModelScope.launch {
            eventDispatcher(event)
        }
    }

    private fun eventDispatcher(event: CalculatorUiState.Event): Any = when (event) {
        CalculatorUiState.Event.Init -> init()
    }

    private fun init() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    drinkTypes = getDrinkTypesUseCase()
                )
            }
        }
    }
}