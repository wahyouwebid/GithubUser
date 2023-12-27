package com.wahyouwebid.githubapp.features.favourite.domain

import androidx.paging.PagingData
import com.wahyouwebid.githubapp.common.utils.Resource
import com.wahyouwebid.githubapp.features.favourite.domain.model.FavouriteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by wahyouwebid on 30 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

class FavouriteUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {

    fun getFavourite(): Flow<PagingData<FavouriteItem>> = repository.getFavourite()

    fun searchFavourite(query: String): Flow<PagingData<FavouriteItem>> = repository.searchFavourite(query)

    fun getFavouriteById(login: String): Flow<Resource<FavouriteItem>> = repository.getFavouriteById(login)

    suspend fun addFavourite(data: FavouriteItem) {
        repository.addFavourite(data)
    }

    suspend fun deleteFavourite(data: FavouriteItem) {
        repository.deleteFavourite(data)
    }

}