package com.wahyouwebid.githubapp.features.favourite.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavouriteItem(
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