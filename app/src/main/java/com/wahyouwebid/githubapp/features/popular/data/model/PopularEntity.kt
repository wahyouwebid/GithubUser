package com.wahyouwebid.githubapp.features.popular.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "popular_entity")
data class PopularEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
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
): Parcelable