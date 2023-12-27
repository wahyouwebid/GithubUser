package com.wahyouwebid.githubapp.features.favourite.domain

import androidx.paging.PagingData
import com.wahyouwebid.githubapp.common.utils.Resource
import com.wahyouwebid.githubapp.features.favourite.domain.model.FavouriteItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by wahyouwebid on 30 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

interface FavouriteRepository {

    fun getFavourite(): Flow<PagingData<FavouriteItem>>

    fun searchFavourite(query: String): Flow<PagingData<FavouriteItem>>

    fun getFavouriteById(login: String): Flow<Resource<FavouriteItem>>

    suspend fun addFavourite(favourite: FavouriteItem)

    suspend fun deleteFavourite(favourite: FavouriteItem)

}