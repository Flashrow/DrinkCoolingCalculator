package pl.flashrow.calculator.presentation.results

internal interface ResultsContract {
    data class State(
        val isLoading: Boolean = false,
    )

    sealed interface Event {
        data object Init : Event
    }
}