package pl.flashrow.navigation

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs

@Composable
fun DestinationsRoot() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}