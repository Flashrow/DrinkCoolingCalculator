package pl.flashrow.calculator.presentation.results

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator.popBackStack
import pl.flashrow.core.common.extension.toHourSecondsText
import pl.flashrow.designsystem.Dimens
import pl.flashrow.ui.DccThemedBackground
import pl.flashrow.ui.widgets.BaseFilledButton
import kotlin.time.Duration

@Composable
fun ResultsScreen(coolingTime: Duration) {
    val viewModel: ResultsViewModel = hiltViewModel()
    ResultScreenContent(
        coolingTime = coolingTime
    )
}

@Composable
private fun ResultScreenContent(
    coolingTime: Duration,
) {
    val context = LocalContext.current
    DccThemedBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Czas chłodzenia wynosi:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                modifier = Modifier
                    .size(350.dp)
                    .background(
                        MaterialTheme.colorScheme.tertiary,
                        shape = CircleShape
                    )
                    .clickable {
                        setTimer(
                            context = context,
                            durationSeconds = coolingTime.inWholeSeconds.toInt()
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = coolingTime.toHourSecondsText(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiary
                )
                Text(
                    text = "Ustaw minutnik",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 30.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
            BaseFilledButton(
                text = "Oblicz ponownie",
                modifier = Modifier.padding(bottom = Dimens.baseMargin),
                onClick = { popBackStack() },
            )
        }
    }
}

private fun setTimer(context: Context, durationSeconds: Int, message: String? = null) {
    val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
        putExtra(AlarmClock.EXTRA_LENGTH, durationSeconds)
        if (message != null) {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
        }
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

@Composable
@Preview
fun ResultsScreenPreview() {
    ResultScreenContent(Duration.parseIsoString("PT1H30M"))
}