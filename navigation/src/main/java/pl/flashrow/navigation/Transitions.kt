package pl.flashrow.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object Transitions {
    object SlideUp : DestinationStyle.Animated {
        override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
            return fadeIn(animationSpec = tween(500))
        }

        override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
            return fadeOut(animationSpec = tween(500))
        }
    }

    object SlideOver : DestinationStyle.Animated {
        override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
            return slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(700)
            )
        }

        override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
            return fadeOut(animationSpec = tween(500))
        }
    }
}
