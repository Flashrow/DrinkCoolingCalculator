package pl.flashrow.calculator.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import pl.flashrow.calculator.presentation.results.ResultsScreen
import pl.flashrow.calculator.presentation.setparams.CalculatorScreen

const val CALCULATOR_ROUTE = "calculator_route"
const val RESULTS_ROUTE = "results_route"

fun NavController.navigateToCalculator(navOptions: NavOptions) = navigate(CALCULATOR_ROUTE, navOptions)

fun NavController.navigateToResults(navOptions: NavOptions) = navigate(RESULTS_ROUTE, navOptions)


fun NavGraphBuilder.calculatorScreen(){
    composable(route = CALCULATOR_ROUTE){
        CalculatorScreen()
    }
}

fun NavGraphBuilder.resultsScreen() {
    composable(route = RESULTS_ROUTE) {
        ResultsScreen()
    }
}