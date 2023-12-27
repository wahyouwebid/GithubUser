package com.wahyouwebid.githubapp.features.popular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahyouwebid.githubapp.features.popular.domain.PopularUseCase
import com.wahyouwebid.githubapp.features.popular.domain.model.PopularItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by wahyouwebid on 29 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: PopularUseCase
): ViewModel() {

    var popular: Flow<PagingData<PopularItem>> = popularUseCase.getPopular("user").cachedIn(viewModelScope)

    fun searchUser(query: String) {
        popular = popularUseCase.getPopular(query).cachedIn(viewModelScope)
    }
}