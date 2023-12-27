package com.wahyouwebid.githubapp.common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

@Composable
fun CustomImageViewFromURL(
    modifier: Modifier,
    image: String?,
    defaultImage: Int
) {
    AsyncImage(
        modifier = modifier,
        model = image,
        contentDescription = null,
        placeholder = painterResource(id = defaultImage),
        error = painterResource(id = defaultImage)
    )
}
