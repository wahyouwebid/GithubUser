package com.wahyouwebid.githubapp.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahyouwebid.githubapp.common.theme.ColorNavy
import com.wahyouwebid.githubapp.common.theme.ColorWhite

/**
 * Created by wahyouwebid on 02 December 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

@Composable
fun ToolbarWithSearch(
    labelToolbar: Int,
    placeHolder: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.background(ColorNavy)
    ) {
        Text(
            text = stringResource(id = labelToolbar),
            style = TextStyle(
                color = ColorWhite,
                fontSize = 20.sp
            ),
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
        )

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp,
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = ColorNavy,
                    contentDescription = null,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .size(24.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Box {
                    BasicTextField(
                        value = value,
                        onValueChange = { onValueChange(it) },
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = ColorNavy
                        ),
                        modifier = modifier
                            .fillMaxWidth(),
                    )
                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(id = placeHolder),
                            color = Color.Black,
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                }
            }
        }
    }
}