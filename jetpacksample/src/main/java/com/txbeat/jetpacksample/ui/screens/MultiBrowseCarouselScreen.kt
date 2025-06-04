package com.txbeat.jetpacksample.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.txbeat.jetpacksample.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiBrowseCarouselScreen() {
    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        val contentDescription: String
    )

    val items = listOf(
        CarouselItem(0, R.drawable.cupcake, "Cupcake"),
        CarouselItem(1, R.drawable.donut, "Donut"),
        CarouselItem(2, R.drawable.eclair, "Eclair"),
        CarouselItem(3, R.drawable.froyo, "Froyo"),
        CarouselItem(4, R.drawable.gingerbread, "Gingerbread")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 48.dp)
            .testTag("MultiBrowseCarouselContainer"),
        contentAlignment = Alignment.Center
    ) {
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.size },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .testTag("HorizontalMultiBrowseCarousel"),
            preferredItemWidth = 250.dp,
            itemSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) { index ->
            val item = items[index]
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(280.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
                    .testTag("CarouselImage_${item.contentDescription}")
            )
        }
    }
}