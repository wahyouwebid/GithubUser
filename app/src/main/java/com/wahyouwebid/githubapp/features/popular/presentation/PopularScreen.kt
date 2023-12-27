package com.wahyouwebid.githubapp.features.popular.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.wahyouwebid.githubapp.R
import com.wahyouwebid.githubapp.common.components.CardPopularItem
import com.wahyouwebid.githubapp.common.components.ToolbarWithSearch

/**
 * Created by wahyouwebid on 29 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

@Composable
fun PopularScreen(
    viewModel: PopularViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {

    val popular = viewModel.popular.collectAsLazyPagingItems()
    var searchText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        ToolbarWithSearch(
            R.string.title_toolbar_user,
            R.string.title_search_user,
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.searchUser(searchText)
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            if(popular.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Column(modifier = Modifier.fillMaxSize()) {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        items(popular) { popular ->
                            if(popular != null) {
                                CardPopularItem(
                                    item = popular,
                                    onItemClick = {
                                        navigateToDetail.invoke(it)
                                    }
                                )
                            }
                        }
                        item {
                            if(popular.loadState.append is LoadState.Loading) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = popular.loadState) {
        if(popular.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (popular.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
