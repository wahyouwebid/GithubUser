package com.wahyouwebid.githubapp.features.popular.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahyouwebid.githubapp.features.popular.data.model.PopularEntity

@Dao
interface PopularDao {

    @Query("SELECT * FROM popular_entity")
    fun getAll(): PagingSource<Int, PopularEntity>

    @Query("SELECT * FROM popular_entity WHERE login = :login")
    suspend fun getById(login: String): PopularEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsertAll(populars: List<PopularEntity>)

    @Query("DELETE FROM popular_entity")
    suspend fun clearAll()
}