package pl.flashrow.calculator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import pl.flashrow.calculator.presentation.CalculatorScreen

const val CALCULATOR_ROUTE = "calculator_route"

fun NavController.navigateToCalculator(navOptions: NavOptions) = navigate(CALCULATOR_ROUTE, navOptions)

fun NavGraphBuilder.calculatorScreen(){
    composable(route = CALCULATOR_ROUTE){
        CalculatorRoute()
    }
}

@Composable
internal fun CalculatorRoute() {
    CalculatorScreen()
}