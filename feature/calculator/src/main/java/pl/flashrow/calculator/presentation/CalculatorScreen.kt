package pl.flashrow.calculator.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
public fun CalculatorScreen (

) {
    val viewModel: CalculatorViewModel = hiltViewModel()
    CalculatorContent()
}

@Composable
private fun CalculatorContent() {

}