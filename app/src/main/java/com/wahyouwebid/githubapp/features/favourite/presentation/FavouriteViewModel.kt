package com.wahyouwebid.githubapp.features.favourite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.wahyouwebid.githubapp.features.favourite.domain.FavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by wahyouwebid on 29 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase
): ViewModel() {

    var favourite = favouriteUseCase.getFavourite().cachedIn(viewModelScope)


    fun searchFavourite(query: String) {
        favourite = favouriteUseCase.searchFavourite(query).cachedIn(viewModelScope)
    }

}