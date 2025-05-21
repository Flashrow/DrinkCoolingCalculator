package pl.flashrow.navigation

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberNavHostEngine
import com.ramcosta.composedestinations.generated.NavGraphs

@Composable
fun DestinationsRoot() {
    val engine = rememberNavHostEngine()
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = engine
    )
}