package pl.flashrow.drinkcoolingcalculator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberDccAppState(
    navController: NavHostController = rememberNavController(),
): DccAppState {
    return remember(
        navController,
    ) {
        DccAppState(
            navController = navController,
        )
    }
}

@Stable
class DccAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination
}