package pl.flashrow.drinkcoolingcalculator.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import pl.flashrow.designsystem.component.DccBackground
import pl.flashrow.drinkcoolingcalculator.navigation.DccNavHost

@Composable
fun DccApp(
    windowSizeClass: WindowSizeClass,
    appState: DccAppState = rememberDccAppState(
        windowSizeClass = windowSizeClass,
    ),
) {
    DccBackground {
        val snackbarHostState = remember { SnackbarHostState() }
        DccNavHost(appState = appState)
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
private fun DccAppPreview() {
    DccApp(windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(340.dp, 680.dp)))
}