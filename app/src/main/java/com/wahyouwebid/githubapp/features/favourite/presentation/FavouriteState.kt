package com.wahyouwebid.githubapp.features.favourite.presentation

import com.wahyouwebid.githubapp.features.favourite.domain.model.FavouriteItem

data class FavouriteState(
    val isLoading: Boolean = false,
    val data: FavouriteItem? = null,
    val error: String = ""
)