package com.wahyouwebid.githubapp.features.detail.domain

data class DetailItem(
    val id: Int?,
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
