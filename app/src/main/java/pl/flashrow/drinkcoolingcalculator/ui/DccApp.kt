import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import pl.flashrow.designsystem.component.DccBackground
import pl.flashrow.drinkcoolingcalculator.ui.DccAppState
import pl.flashrow.drinkcoolingcalculator.ui.rememberDccAppState

@Composable
fun DccApp(
    appState: DccAppState = rememberDccAppState(),
) {
    DccBackground {
        Text("Drink Cooling Calculator App")
    }
}