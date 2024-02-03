package pl.flashrow.drinkcoolingcalculator.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberDccAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): DccAppState {
    return remember(
        windowSizeClass,
        coroutineScope,
        navController,
    ) {
        DccAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            windowSizeClass = windowSizeClass,
        )
    }
}

@Stable
class DccAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination
}