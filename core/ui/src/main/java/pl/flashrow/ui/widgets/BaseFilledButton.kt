package pl.flashrow.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.flashrow.designsystem.Dimens

@Composable
fun BaseFilledButton(
    text: String = "",
    modifier: Modifier = Modifier,
    child: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(Dimens.buttonHeight)
            .fillMaxWidth(),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        if (child != null) {
            child()
        } else {
            Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Preview
@Composable
private fun BaseButtonPreview() {
    BaseOutlinedButton(text = "Button", onClick = { })
}