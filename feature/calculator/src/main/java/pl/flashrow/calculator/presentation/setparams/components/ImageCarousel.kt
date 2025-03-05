package pl.flashrow.calculator.presentation.setparams.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.launch
import pl.flashrow.core.common.extension.getPercentageString
import pl.flashrow.dcc.core.model.DrinkType
import pl.flashrow.dcc.core.resources.R
import kotlin.math.absoluteValue

@Composable
fun ImageCarousel(drinkTypes: List<DrinkType>, onPageChange: (Int) -> Unit){
    ImageCarouselContent(drinkTypes, onPageChange)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ImageCarouselContent(
    drinksList: List<DrinkType>,
    onPageChange: (Int) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        drinksList.size
    })
    val scope = rememberCoroutineScope()
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(1)
    )
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { currentPage ->
                onPageChange(currentPage)
            }
    }

    HorizontalPager(
        state = pagerState,
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
                .clip(RoundedCornerShape(28.dp))
                .graphicsLayer {
                    alpha = lerp(
                        start = 0.7f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
            onClick = {
                scope.launch { pagerState.animateScrollToPage(page) }
                },
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
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart,
    ) {
        Image(
            painter = painterResource(id = drinkType.resourceId),
            contentDescription = drinkType.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Box(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(
                    color = Color.Black.copy(alpha = 0.2f),
                ),
        ) {
            Text(
                drinkType.name + " ~" + drinkType.alcoholPercentage.getPercentageString(),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.background
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
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
        ),
        onPageChange = {}
    )
}

@Preview
@Composable
private fun CarouselItemPreview() {
    CarouselItem(
        DrinkType(
            resourceId = R.drawable.beer_icon,
            name = "Beer",
            alcoholPercentage = 0.04f
        ),
    )
}