package pl.flashrow.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.flashrow.designsystem.Dimens

@Composable
fun ErrorBox(
    modifier: Modifier = Modifier,
    isError: Boolean,
    content: @Composable () -> Unit = { },
) {
    Box(
        modifier = modifier
            .padding(
                top = Dimens.verticalSectionMargin,
            )
            .then(
                if (isError) {
                    Modifier
                        .background(
                            MaterialTheme.colorScheme.error.copy(alpha = 0.05f),
                            shape = MaterialTheme.shapes.extraLarge
                        )
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.error,
                            shape = MaterialTheme.shapes.extraLarge
                        )
                        .padding(Dimens.baseMargin)
                } else Modifier
            ),
    ) {
        content()
    }
}