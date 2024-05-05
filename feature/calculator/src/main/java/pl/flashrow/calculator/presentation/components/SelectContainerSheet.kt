package pl.flashrow.calculator.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SelectContainerSheet(onClick: () -> Unit) {
    Column {
        Text(text = "Wybierz rodzaj pojemnika")
    }
}

@Preview
@Composable
fun SelectContainerSheetPreview() {
    SelectContainerSheet {}
}