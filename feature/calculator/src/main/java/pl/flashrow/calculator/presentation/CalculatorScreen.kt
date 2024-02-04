package pl.flashrow.calculator.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SportsBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.flashrow.dcc.feature.calculator.R
import pl.flashrow.designsystem.Dimens
import pl.flashrow.ui.DccThemedBackground

@Composable
fun CalculatorScreen(

) {
    val viewModel: CalculatorViewModel = hiltViewModel()
    CalculatorContent()
}

@Composable
private fun CalculatorContent() {
    DccThemedBackground {
        Column(modifier = Modifier.padding(horizontal = Dimens.baseMargin)) {
            Text(
                stringResource(id = R.string.calculate_drink_cooling_time_title),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row {
                Icon(Icons.Outlined.SportsBar, contentDescription = "")
                Text("Wybierz rodzaj napoju", style = MaterialTheme.typography.titleMedium)
            }
            ImageCarousel(listOf())
        }
    }
}

@Preview
@Composable
private fun CalculatorPreview() {
    CalculatorContent()
}