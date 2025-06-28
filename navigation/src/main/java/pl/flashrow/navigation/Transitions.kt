package pl.flashrow.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.ramcosta.composedestinations.spec.DestinationStyle

object Transitions {
    val slideUp = DestinationStyle.Animated {
        val tweenSpec = tween<Float>(500)
        fadeIn(animationSpec = tweenSpec)
    }

    val slideDown = DestinationStyle.Animated {
        val tweenSpec = tween<Float>(500)
        fadeOut(animationSpec = tweenSpec)
    }

    val slideOver = DestinationStyle.Animated {
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
            animationSpec = tween(700)
        )
    }
}
