package com.wahyouwebid.githubapp.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahyouwebid.githubapp.common.theme.ColorWhite

/**
 * Created by wahyouwebid on 02 December 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

@Composable
fun CardWithLabel(label: String?, value: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(80.dp)
            .background(ColorWhite)
            .shadow(4.dp, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = label.orEmpty(),
                style = TextStyle(fontSize = 18.sp),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = value.orEmpty(),
                style = TextStyle(fontSize = 11.sp),
                color = Color.Black
            )
        }
    }
}