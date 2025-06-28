package pl.flashrow.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
    iterations: Int = 1,
    contentScale: ContentScale = ContentScale.Fit,
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
        if (progress == 1f) {
            currentOnAnimationEnd()
        }
    }

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier,
        contentScale = contentScale
    )
}
