package pl.flashrow.navigation.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.flashrow.calculator.presentation.results.ResultsScreen

@Destination<RootGraph>
@Composable
internal fun Results(navigator: DestinationsNavigator) {
    ResultsScreen()
}