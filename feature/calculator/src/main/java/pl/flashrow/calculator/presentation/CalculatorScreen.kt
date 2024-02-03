package pl.flashrow.calculator.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CalculatorScreen (

) {
    val viewModel: CalculatorViewModel = hiltViewModel()
    CalculatorContent()
}

@Composable
private fun CalculatorContent() {

}

@Preview
@Composable
private fun CalculatorPreview(){
    CalculatorContent()
}