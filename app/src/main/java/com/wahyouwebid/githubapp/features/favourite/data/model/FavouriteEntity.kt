package com.wahyouwebid.githubapp.features.favourite.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_entity")
data class FavouriteEntity(
    @PrimaryKey
    val idUser: Int?,
    val login: String?,
    val avatarUrl: String?,
    val type: String?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val publicRepos: String?,
    val followers: String?,
    val following: String?,
    val bio: String?,
)