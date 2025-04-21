package pl.flashrow.navigation.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.flashrow.calculator.presentation.calculator.CalculatorScreen
import pl.flashrow.navigation.provided.ProvidedCalculatorNavigation

@Destination<RootGraph>(start = true)
@Composable
fun Calculator(navigator: DestinationsNavigator) {
    CalculatorScreen(navigation = ProvidedCalculatorNavigation(navigator))
}