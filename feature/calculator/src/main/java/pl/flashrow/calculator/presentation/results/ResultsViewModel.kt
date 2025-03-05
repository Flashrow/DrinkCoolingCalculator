package pl.flashrow.calculator.presentation.results

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(ResultsUiState.UiState())
    val uiState: StateFlow<ResultsUiState.UiState> = _uiState

}