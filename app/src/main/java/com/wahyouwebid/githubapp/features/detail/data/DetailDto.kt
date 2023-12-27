package com.wahyouwebid.githubapp.features.detail.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DetailDto(
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("login")
    val login: String?,

    @field:SerializedName("avatar_url")
    val avatarUrl: String?,

    @field:SerializedName("type")
    val type: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("company")
    val company: String?,

    @field:SerializedName("blog")
    val blog: String?,

    @field:SerializedName("location")
    val location: String?,

    @field:SerializedName("public_repos")
    val publicRepos: String?,

    @field:SerializedName("followers")
    val followers: String?,

    @field:SerializedName("following")
    val following: String?,

    @field:SerializedName("bio")
    val bio: String?,
)