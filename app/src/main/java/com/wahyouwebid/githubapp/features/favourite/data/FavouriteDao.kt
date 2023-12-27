package com.wahyouwebid.githubapp.features.favourite.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahyouwebid.githubapp.features.favourite.data.model.FavouriteEntity

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: FavouriteEntity)

    @Query("SELECT * FROM favourite_entity")
    fun getFavourite(): PagingSource<Int, FavouriteEntity>

    @Query("SELECT * FROM favourite_entity WHERE login = :login")
    suspend fun getFavouriteById(login: String): FavouriteEntity

    @Query("SELECT * FROM favourite_entity WHERE login LIKE :query")
    fun searchFavourite(query : String): PagingSource<Int, FavouriteEntity>

    @Delete
    suspend fun deleteFavourite(favourite: FavouriteEntity)

}