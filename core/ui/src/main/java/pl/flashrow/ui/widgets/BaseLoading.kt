package pl.flashrow.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.flashrow.designsystem.component.DccBackground
import pl.flashrow.designsystem.theme.DccTheme

@Composable
fun BaseLoading() {
    DccTheme {
        DccBackground {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LinearProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
private fun BaseLoadingPreview() {
    BaseLoading()
}