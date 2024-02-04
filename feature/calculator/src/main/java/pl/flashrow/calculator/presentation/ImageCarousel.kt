package pl.flashrow.calculator.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import pl.flashrow.dcc.core.model.DrinkType
import pl.flashrow.dcc.core.resources.R
import kotlin.math.absoluteValue

@Composable
fun ImageCarousel(drinkTypes: List<DrinkType>) {
    ImageCarouselContent(drinkTypes)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImageCarouselContent(
    drinksList: List<DrinkType>,
) {
    val pagerState = rememberPagerState(pageCount = {
        drinksList.size
    })
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(1)
    )
    HorizontalPager(
        state = pagerState,
        beyondBoundsPageCount = 5,
        flingBehavior = fling,
        contentPadding = PaddingValues(
            end = 100.dp,
        ),
    ) { page ->
        val pageOffset = (
                (pagerState.currentPage - page) + pagerState
                    .currentPageOffsetFraction
                ).absoluteValue
        Card(
            modifier = Modifier
                .width(270.dp)
                .height(240.dp)
                .graphicsLayer {
                    alpha = lerp(
                        start = 0.7f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            CarouselItem(drinksList[page])
        }
    }
}

@Composable
private fun CarouselItem(
    drinkType: DrinkType,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = drinkType.resourceId),
            contentDescription = drinkType.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
}

@Preview
@Composable
private fun ImageCarouselPreview() {
    ImageCarouselContent(
        listOf(
            DrinkType(
                resourceId = R.drawable.beer_icon,
                name = "Beer",
                alcoholPercentage = 0.04f
            ),
            DrinkType(
                resourceId = R.drawable.spirit_icon,
                name = "Spirit",
                alcoholPercentage = 0.40f
            ),
            DrinkType(
                resourceId = R.drawable.wine_icon,
                name = "Wine",
                alcoholPercentage = 0.13f
            ),
            DrinkType(
                resourceId = R.drawable.tea_icon,
                name = "Tea",
                alcoholPercentage = 0f
            ),
            DrinkType(
                resourceId = R.drawable.soft_drink_icon,
                name = "Soft drink",
                alcoholPercentage = 0f
            ),
        )
    )
}