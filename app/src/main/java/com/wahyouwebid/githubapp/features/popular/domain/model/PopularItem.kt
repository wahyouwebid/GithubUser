package com.wahyouwebid.githubapp.features.popular.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularItem(
    val id: Int?,
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