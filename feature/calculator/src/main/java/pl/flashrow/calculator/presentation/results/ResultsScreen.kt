package pl.flashrow.calculator.presentation.results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.flashrow.core.common.extension.toHhMm
import pl.flashrow.ui.widgets.BaseFilledButton
import kotlin.time.Duration

@Composable
fun ResultsScreen(coolingTime: Duration) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "9:30",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Czas chłodzenia piwa w butelce\n500ml z 21°C do temperatury 3°C,\nkorzystając z zamrażarki wynosi:",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Yellow, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = coolingTime.toHhMm(),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BaseFilledButton(
                onClick = {  },
                text = "Ustaw minutnik",
                modifier = Modifier.padding(bottom = 30.dp)
            )
            BaseFilledButton(
                onClick = { },
                text = "Oblicz ponownie",)
        }
    }
}

@Composable
@Preview
fun ResultsScreenPreview() {
    ResultsScreen(Duration.parse("20:30"))
}