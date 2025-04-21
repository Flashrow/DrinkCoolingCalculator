package pl.flashrow.navigation.provided

import com.ramcosta.composedestinations.generated.destinations.ResultsDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.flashrow.calculator.presentation.calculator.CalculatorNavigation

internal class ProvidedCalculatorNavigation (navigator: DestinationsNavigator) : CalculatorNavigation, DestinationsNavigator by navigator {
    override fun navigateToResultsScreen() {
        navigate(
            ResultsDestination()
        )
    }
}