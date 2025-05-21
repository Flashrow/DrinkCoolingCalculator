package pl.flashrow.navigation.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.flashrow.calculator.presentation.results.ResultsScreen
import pl.flashrow.navigation.provided.ProvidedResultNavigation
import pl.flashrow.navigation.transitions.ResultsScreenTransition

@Destination<RootGraph>(style = ResultsScreenTransition::class)
@Composable
fun Results(navigator: DestinationsNavigator, result: ResultsNavArgs) {
    ResultsScreen(
        coolingTime = result.coolingTime,
        navigation = ProvidedResultNavigation(navigator)
    )
}