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
    text: String = "",
    child: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    isError: Boolean = false,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.height(Dimens.buttonHeight).fillMaxWidth(),
        enabled = enabled,
        border = BorderStroke(Dimens.borderWidth,if(isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary),
    ) {
        if(child != null) {
            child()
        } else {
            Text(text = text, color = if(isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview
@Composable
private fun BaseButtonPreview() {
    BaseOutlinedButton(text = "Button", onClick = { })
}