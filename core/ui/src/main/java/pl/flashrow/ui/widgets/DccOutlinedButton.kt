package pl.flashrow.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.flashrow.designsystem.Dimens

@Composable
fun BaseOutlinedButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.height(Dimens.buttonHeight).fillMaxWidth(),
        enabled = enabled,
        border = BorderStroke(Dimens.borderWidth, MaterialTheme.colorScheme.primary),
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.primary)
    }
}

@Preview
@Composable
private fun BaseButtonPreview() {
    BaseOutlinedButton(text = "Button", onClick = { })
}