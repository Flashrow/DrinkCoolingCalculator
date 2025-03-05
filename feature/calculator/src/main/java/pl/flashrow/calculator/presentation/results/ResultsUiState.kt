package pl.flashrow.calculator.presentation.results

sealed interface ResultsUiState {
    data class UiState(
        val result: String = "",
        val isLoading: Boolean = false
    ) : ResultsUiState
}

sealed interface ResultsUiEvent {
    data object Init : ResultsUiEvent
    data class UpdateResult(val result: String) : ResultsUiEvent
    data class UpdateLoading(val isLoading: Boolean) : ResultsUiEvent
}