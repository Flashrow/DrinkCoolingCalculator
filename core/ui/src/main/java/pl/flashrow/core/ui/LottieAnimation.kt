package pl.flashrow.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState

@Composable
fun LottieAnimationPlayer(
    modifier: Modifier = Modifier,
    animationResId: Int,
    isPlaying: Boolean = true,
    restartOnPlay: Boolean = true,
    speed: Float = 1f,
    iterations: Int = 1,
    onAnimationEnd: () -> Unit = {}
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationResId))
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = isPlaying,
        restartOnPlay = restartOnPlay,
        speed = speed,
        iterations = iterations
    )
    val currentOnAnimationEnd by rememberUpdatedState(onAnimationEnd)

    LaunchedEffect(progress) {
        if (progress == 1f) { // Animation considered ended when progress reaches 1f
            currentOnAnimationEnd()
        }
    }

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}
