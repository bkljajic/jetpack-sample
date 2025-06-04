package com.txbeat.jetpacksample.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.txbeat.jetpacksample.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenMultiBrowseCarouselScreen() {
    data class ImageItem(
        val id: Int,
        @DrawableRes val imageRes: Int,
        val description: String
    )

    val images = listOf(
        ImageItem(0, R.drawable.cupcake, "Cupcake"),
        ImageItem(1, R.drawable.donut, "Donut"),
        ImageItem(2, R.drawable.eclair, "Eclair"),
        ImageItem(3, R.drawable.froyo, "Froyo"),
        ImageItem(4, R.drawable.gingerbread, "Gingerbread")
    )

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    HorizontalUncontainedCarousel(
        state = rememberCarouselState { images.size },
        modifier = Modifier
            .fillMaxSize()
            .testTag("FullScreenCarousel"),
        itemWidth = screenWidth,
        contentPadding = PaddingValues(0.dp)
    ) { index ->
        val item = images[index]
        Box(
            modifier = Modifier
                .width(screenWidth)
                .height(screenHeight)
                .testTag("CarouselItemBox_${item.id}")
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("CarouselImage_${item.description}")
            )
        }
    }
}
