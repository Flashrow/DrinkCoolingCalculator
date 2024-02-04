package pl.flashrow.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.flashrow.designsystem.component.DccBackground
import pl.flashrow.designsystem.theme.DccTheme

@Composable
fun DccThemedBackground (
    safeSpace: Boolean = true,
    content: @Composable () -> Unit
) {
    DccTheme {
        DccBackground {
            Column {
                if(safeSpace) Spacer(modifier = Modifier.height(60.dp))
                content()
            }
        }
    }
}