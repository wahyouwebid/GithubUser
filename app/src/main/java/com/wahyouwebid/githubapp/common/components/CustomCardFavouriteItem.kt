package com.wahyouwebid.githubapp.common.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.wahyouwebid.githubapp.R
import com.wahyouwebid.githubapp.features.favourite.domain.model.FavouriteItem

@Composable
fun CardFavouriteItem(
    item: FavouriteItem,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { item.login?.let { onItemClick(it) } }
            .padding(12.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomImageViewFromURL(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .border(
                        width = dimensionResource(id = R.dimen.user_detail_screen_profile_image_border_width),
                        color = Color.White,
                        CircleShape
                    )
                    .padding(top = 4.dp)
                    .clip(CircleShape),
                item.avatarUrl,
                R.drawable.logo_github_small
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.login.toString(),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}