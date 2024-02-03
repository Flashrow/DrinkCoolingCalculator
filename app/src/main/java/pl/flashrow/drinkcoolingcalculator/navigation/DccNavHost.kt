package pl.flashrow.drinkcoolingcalculator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import pl.flashrow.calculator.navigation.CALCULATOR_ROUTE
import pl.flashrow.calculator.navigation.calculatorScreen
import pl.flashrow.drinkcoolingcalculator.ui.DccAppState

@Composable
fun DccNavHost(
    appState: DccAppState,
    modifier: Modifier = Modifier,
    startDestination: String = CALCULATOR_ROUTE,
){
    val navController = appState.navController
    NavHost(navController = navController,
            startDestination = startDestination,
            modifier = modifier) {
        calculatorScreen()
    }
}