package com.wahyouwebid.githubapp.common.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wahyouwebid.githubapp.common.theme.ColorNavy

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun FavoriteButton(
    isChecked: Boolean,
    onClick: () -> Unit
) {
    var icon by remember { mutableStateOf(if (isChecked) Icons.Default.Favorite else Icons.Default.FavoriteBorder) }

    Card(
        modifier = Modifier
            .size(40.dp),
        shape = CircleShape
    ) {
        IconToggleButton(
            checked = isChecked,
            onCheckedChange = {
                onClick()
                icon = if (icon == Icons.Default.Favorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite
            }
        ) {
            val transition = updateTransition(isChecked, label = "Checked indicator")

            val size by transition.animateDp(
                transitionSpec = {
                    if (false isTransitioningTo true) {
                        keyframes {
                            durationMillis = 250
                            30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                            35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                            40.dp at 75 // ms
                            35.dp at 150 // ms
                        }
                    } else {
                        spring(stiffness = Spring.StiffnessVeryLow)
                    }
                },
                label = "Size"
            ) { 24.dp }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = ColorNavy,
                modifier = Modifier.size(size)
            )

        }
    }
}
