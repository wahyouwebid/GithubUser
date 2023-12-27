package com.wahyouwebid.githubapp.features.detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wahyouwebid.githubapp.R
import com.wahyouwebid.githubapp.common.components.CustomImageViewFromURL
import com.wahyouwebid.githubapp.common.components.ErrorText
import com.wahyouwebid.githubapp.common.components.FavoriteButton
import com.wahyouwebid.githubapp.common.components.CardWithLabel
import com.wahyouwebid.githubapp.common.theme.ColorNavy
import com.wahyouwebid.githubapp.common.theme.ColorWhite
import com.wahyouwebid.githubapp.core.mapper.Mapper.popularItemToFavouriteItem
import com.wahyouwebid.githubapp.features.detail.domain.DetailItem
import com.wahyouwebid.githubapp.features.favourite.presentation.FavouriteState

/**
 * Created by wahyouwebid on 29 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

@Composable
fun PopularDetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navigationBack: () -> Unit
) {

    val detailState = viewModel.detailState.value
    val favouriteState = viewModel.favouriteState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        detailState.data?.let { item ->
            HeaderDetail(item, favouriteState, viewModel, navigationBack)
        }

        if (detailState.error.isNotBlank()) ErrorText(
            detailState.error,
            Modifier.align(Alignment.CenterHorizontally)
        )

        if (detailState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = ColorWhite
            )
        }
    }
}

@Composable
fun HeaderDetail(
    item: DetailItem,
    favouriteState: FavouriteState,
    viewModel: DetailViewModel,
    navigationBack: () -> Unit
) {
    val favouriteItem = favouriteState.data

    item.let {
        Box(
            modifier = Modifier
                .background(
                    color = ColorNavy,
                    shape = RoundedCornerShape(
                        bottomStart = 32.dp,
                        bottomEnd = 32.dp
                    )
                )
                .padding(24.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                Card (
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navigationBack.invoke() },
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = ColorNavy,
                        contentDescription = null,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .padding(8.dp)
                            .size(24.dp)
                            .clip(
                                shape = RoundedCornerShape(
                                    bottomStart = 16.dp,
                                    bottomEnd = 16.dp
                                )
                            ),
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                val (isChecked, setChecked) = remember { mutableStateOf(favouriteItem?.idUser != null) }
                FavoriteButton(
                    isChecked = isChecked,
                    onClick = {
                        setChecked(!isChecked)
                        setFavoriteUser(viewModel, isChecked, item)
                    }
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(24.dp))

                CustomImageViewFromURL(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(
                            width = dimensionResource(id = R.dimen.user_detail_screen_profile_image_border_width),
                            color = Color.White,
                            CircleShape
                        )
                        .padding(top = 4.dp)
                        .clip(CircleShape),
                    item.avatarUrl,
                    R.drawable.logo_github
                )

                Spacer(modifier = Modifier.size(24.dp))

                Text(
                    text = item.login.orEmpty(),
                    style = TextStyle(fontSize = 22.sp),
                    color = ColorWhite
                )

                Spacer(modifier = Modifier.size(12.dp))

                Text(
                    text = item.blog.orEmpty(),
                    style = TextStyle(fontSize = 13.sp),
                    color = ColorWhite
                )
            }
        }

        Spacer(modifier = Modifier.size(24.dp))

        CardWithLabel(label = stringResource(id = R.string.title_company_profile), value = item.company ?: "-")

        CardWithLabel(label = stringResource(id = R.string.title_website), value = item.blog ?: "-")

        CardWithLabel(label = stringResource(id = R.string.title_location), value = item.location ?: "-")

        CardWithLabel(label = stringResource(id = R.string.title_bio), value = item.bio ?: "-")

    }
}

private fun setFavoriteUser(
    searchViewModel: DetailViewModel,
    isChecked: Boolean,
    favoriteItem: DetailItem?
) {
    if (isChecked) {
        searchViewModel.deleteFavourite(favoriteItem?.popularItemToFavouriteItem())
    } else {
        favoriteItem.let {
            searchViewModel.addFavourite(it?.popularItemToFavouriteItem())
        }
    }
}
