package pl.flashrow.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimationPlayer(
    modifier: Modifier = Modifier,
    animationResId: Int,
    isPlaying: Boolean = true,
    restartOnPlay: Boolean = true,
    speed: Float = 1f,
    iterations: Int = 1
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationResId))
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = isPlaying,
        restartOnPlay = restartOnPlay,
        speed = speed,
        iterations = iterations
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}
