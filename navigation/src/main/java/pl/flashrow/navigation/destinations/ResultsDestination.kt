package pl.flashrow.navigation.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import pl.flashrow.calculator.presentation.results.ResultsScreen

@Destination<RootGraph>
@Composable
internal fun Results(result: ResultsNavArgs) {
    ResultsScreen(result.coolingTime)
}