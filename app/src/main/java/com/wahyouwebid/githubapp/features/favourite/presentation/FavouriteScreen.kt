package com.wahyouwebid.githubapp.features.favourite.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.wahyouwebid.githubapp.R
import com.wahyouwebid.githubapp.common.components.CardFavouriteItem
import com.wahyouwebid.githubapp.common.components.ToolbarWithSearch

/**
 * Created by wahyouwebid on 30 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

@Composable
fun FavouriteScreen(
    viewModel: FavouriteViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {

    val favourite = viewModel.favourite.collectAsLazyPagingItems()
    var searchText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            ToolbarWithSearch(
                R.string.title_toolbar_favourite,
                R.string.search_favourite,
                value = searchText,
                onValueChange = {
                    searchText = it
                    viewModel.searchFavourite(searchText)
                }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(favourite) { popular ->
                    if(popular != null) {
                        CardFavouriteItem(
                            item = popular,
                            onItemClick = { navigateToDetail.invoke(it) }
                        )
                    }
                }
                item {
                    if(favourite.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
