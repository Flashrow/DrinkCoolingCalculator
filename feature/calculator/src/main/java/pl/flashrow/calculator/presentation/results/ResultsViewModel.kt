package pl.flashrow.calculator.presentation.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ResultsViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(ResultsContract.State())
    val uiState: StateFlow<ResultsContract.State> = _uiState

    fun onEvent(event: ResultsContract.Event) {
        viewModelScope.launch {
            eventDispatcher(event)
        }
    }

    private suspend fun eventDispatcher(event: ResultsContract.Event): Any = when (event) {
        ResultsContract.Event.Init -> {}
    }
}