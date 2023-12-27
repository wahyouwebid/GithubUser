package com.wahyouwebid.githubapp.features.popular.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class PopularDto(
    @field:SerializedName("total_count")
    val totalCount: Int?,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean?,

    @field:SerializedName("items")
    val items: List<DataUserDto>,
)

@Keep
data class DataUserDto(
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
