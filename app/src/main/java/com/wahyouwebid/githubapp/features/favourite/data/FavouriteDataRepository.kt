package com.wahyouwebid.githubapp.features.favourite.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.wahyouwebid.githubapp.common.utils.Resource
import com.wahyouwebid.githubapp.core.mapper.Mapper.favouriteEntityToItem
import com.wahyouwebid.githubapp.core.mapper.Mapper.favouriteItemToEntity
import com.wahyouwebid.githubapp.features.favourite.domain.FavouriteRepository
import com.wahyouwebid.githubapp.features.favourite.domain.model.FavouriteItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by wahyouwebid on 30 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

class FavouriteDataRepository @Inject constructor(
    private val pagingConfig: PagingConfig,
    private val dao: FavouriteDao,
): FavouriteRepository {

    override fun getFavourite(): Flow<PagingData<FavouriteItem>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                dao.getFavourite()
            }
        ).flow.map { entity -> entity.map { it.favouriteEntityToItem() } }
    }

    override fun searchFavourite(query: String): Flow<PagingData<FavouriteItem>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                dao.searchFavourite("%${query}%")
            }
        ).flow.map { entity -> entity.map { it.favouriteEntityToItem() } }
    }

    override fun getFavouriteById(login: String): Flow<Resource<FavouriteItem>> = flow {
        try {
            val response = dao.getFavouriteById(login)
            emit(
                Resource.Success(response.favouriteEntityToItem())
            )

        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    override suspend fun addFavourite(favourite: FavouriteItem) {
        dao.insertFavourite(favourite.favouriteItemToEntity())
    }

    override suspend fun deleteFavourite(favourite: FavouriteItem) {
        dao.deleteFavourite(favourite.favouriteItemToEntity())
    }

}