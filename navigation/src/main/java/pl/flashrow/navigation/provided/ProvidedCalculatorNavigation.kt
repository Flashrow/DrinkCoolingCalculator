package pl.flashrow.navigation.provided

import com.ramcosta.composedestinations.generated.destinations.ResultsDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.flashrow.calculator.presentation.calculator.CalculatorNavigation
import pl.flashrow.navigation.destinations.ResultsNavArgs
import kotlin.time.Duration

internal class ProvidedCalculatorNavigation (navigator: DestinationsNavigator) : CalculatorNavigation, DestinationsNavigator by navigator {
    override fun navigateToResultsScreen(coolingTime: Duration) {
        navigate(
            ResultsDestination(
                ResultsNavArgs(
                    coolingTime = coolingTime,
                )
            )
        )
    }
}