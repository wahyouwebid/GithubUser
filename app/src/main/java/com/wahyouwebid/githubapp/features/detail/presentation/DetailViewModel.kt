package com.wahyouwebid.githubapp.features.detail.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahyouwebid.githubapp.common.utils.Constants
import com.wahyouwebid.githubapp.common.utils.Resource
import com.wahyouwebid.githubapp.features.detail.domain.DetailUseCase
import com.wahyouwebid.githubapp.features.favourite.domain.FavouriteUseCase
import com.wahyouwebid.githubapp.features.favourite.domain.model.FavouriteItem
import com.wahyouwebid.githubapp.features.favourite.presentation.FavouriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by wahyouwebid on 29 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase,
    private val favouriteUseCase: FavouriteUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _detailState = mutableStateOf(DetailState())
    val detailState: State<DetailState> = _detailState

    private val _favouriteState = mutableStateOf(FavouriteState())
    val favouriteState: State<FavouriteState> = _favouriteState


    init {
        savedStateHandle.get<String>(Constants.PARAM_LOGIN)?.let { login ->
            getPopularDetailRemote(login)
            getFavouriteById(login)
        }
    }

    private fun getPopularDetailRemote(login: String) {
        detailUseCase.getPopularDetailRemote(login).onEach { result ->
            when (result) {
                Resource.Loading -> _detailState.value = DetailState(isLoading = true)
                is Resource.Success -> _detailState.value = DetailState(data = result.data)
                is Resource.Error -> {
                    _detailState.value = DetailState(error = result.errorMessage)
                    getPopularDetailLocal(login)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPopularDetailLocal(login: String) {
        detailUseCase.getPopularDetailLocal(login).onEach { result ->
            when (result) {
                Resource.Loading -> _detailState.value = DetailState(isLoading = true)
                is Resource.Success -> _detailState.value = DetailState(data = result.data)
                is Resource.Error -> _detailState.value = DetailState(error = result.errorMessage)
            }
        }.launchIn(viewModelScope)
    }

    private fun getFavouriteById(id: String) {
        favouriteUseCase.getFavouriteById(id).onEach { result ->
            when (result) {
                Resource.Loading -> _favouriteState.value = FavouriteState(isLoading = true)
                is Resource.Success -> _favouriteState.value = FavouriteState(data = result.data)
                is Resource.Error -> _favouriteState.value = FavouriteState(error = result.errorMessage)
            }
        }.launchIn(viewModelScope)
    }

    fun addFavourite(data: FavouriteItem?) {
        viewModelScope.launch {
            if (data != null) {
                favouriteUseCase.addFavourite(data)
            }
        }
    }

    fun deleteFavourite(data: FavouriteItem?) {
        viewModelScope.launch {
            if (data != null) {
                favouriteUseCase.deleteFavourite(data)
            }
        }
    }

}