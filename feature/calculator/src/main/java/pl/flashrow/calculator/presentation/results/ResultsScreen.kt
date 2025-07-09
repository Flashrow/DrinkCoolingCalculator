package pl.flashrow.calculator.presentation.results

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import pl.flashrow.core.common.extension.toHourSecondsText
import pl.flashrow.dcc.core.resources.R
import pl.flashrow.dcc.feature.calculator.BuildConfig
import pl.flashrow.designsystem.Dimens
import pl.flashrow.ui.DccThemedBackground
import pl.flashrow.ui.widgets.BaseFilledButton
import kotlin.time.Duration

@Composable
fun ResultsScreen(
    coolingTime: Duration,
    navigation: ResultNavigation,
) {
    val viewModel: ResultsViewModel = hiltViewModel()
    ResultScreenContent(
        coolingTime = coolingTime,
        navigateBack = {
            navigation.navigateBack()
        }
    )
}

@SuppressLint("MissingPermission")
@Composable
private fun ResultScreenContent(
    coolingTime: Duration,
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current
    val adView = remember { AdView(context) }
    LaunchedEffect(adView) {
        adView.adUnitId = BuildConfig.RESULT_BANNER_AD_ID
        val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, 360)
        adView.setAdSize(adSize)
        val adRequestBuilder = com.google.android.gms.ads.AdRequest.Builder()
        val adRequest = adRequestBuilder.build()
        adView.loadAd(adRequest)
    }

    DccThemedBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.cooling_time_result_description),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                modifier = Modifier
                    .size(350.dp)
                    .background(
                        MaterialTheme.colorScheme.tertiary,
                        shape = CircleShape
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                ),
                onClick = {
                    setTimer(
                        context = context,
                        durationSeconds = coolingTime.inWholeSeconds.toInt()
                    )
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = coolingTime.toHourSecondsText(),
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiary,
                    )
                    Text(
                        text = stringResource(R.string.setup_timer),
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .align(Alignment.BottomCenter),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }

            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .padding(bottom = 10.dp)
                ) {
                    AndroidView(modifier = Modifier.fillMaxWidth(), factory = { adView })
                }
                BaseFilledButton(
                    text = stringResource(R.string.calculate_again),
                    modifier = Modifier.padding(
                        bottom = Dimens.baseMargin,
                        start = 16.dp,
                        end = 16.dp
                    ),
                    onClick = { navigateBack() },
                )
            }
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
    ResultScreenContent(
        coolingTime = Duration.parseIsoString("PT1H30M"),
        navigateBack = {}
    )
}