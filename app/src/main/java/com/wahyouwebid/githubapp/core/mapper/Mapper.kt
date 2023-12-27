package com.wahyouwebid.githubapp.core.mapper
import com.wahyouwebid.githubapp.features.detail.data.DetailDto
import com.wahyouwebid.githubapp.features.detail.domain.DetailItem
import com.wahyouwebid.githubapp.features.favourite.data.model.FavouriteEntity
import com.wahyouwebid.githubapp.features.favourite.domain.model.FavouriteItem
import com.wahyouwebid.githubapp.features.popular.data.model.DataUserDto
import com.wahyouwebid.githubapp.features.popular.data.model.PopularEntity
import com.wahyouwebid.githubapp.features.popular.domain.model.PopularItem

/**
 * Created by wahyouwebid on 30 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

object Mapper {

    fun DataUserDto.popularDtoToEntity(): PopularEntity {
        return PopularEntity(
            id = null,
            idUser = this.id,
            login = this.login,
            avatarUrl = this.avatarUrl,
            type = this.type,
            name = this.name,
            company = this.company,
            blog = this.blog,
            location = this.location,
            publicRepos = this.publicRepos,
            followers = this.followers,
            following = this.following,
            bio = this.bio,
        )
    }

    fun PopularEntity.popularEntityToItem(): PopularItem {
        return PopularItem(
            id = this.id,
            idUser = this.idUser,
            login = this.login,
            avatarUrl = this.avatarUrl,
            type = this.type,
            name = this.name,
            company = this.company,
            blog = this.blog,
            location = this.location,
            publicRepos = this.publicRepos,
            followers = this.followers,
            following = this.following,
            bio = this.bio
        )
    }

    fun PopularEntity.popularEntityToDetailItem(): DetailItem {
        return DetailItem(
            id = this.idUser,
            login = this.login,
            avatarUrl = this.avatarUrl,
            type = this.type,
            name = this.name,
            company = this.company,
            blog = this.blog,
            location = this.location,
            publicRepos = this.publicRepos,
            followers = this.followers,
            following = this.following,
            bio = this.bio
        )
    }

    fun FavouriteEntity?.favouriteEntityToItem(): FavouriteItem {
        return FavouriteItem(
            idUser = this?.idUser,
            login = this?.login,
            avatarUrl = this?.avatarUrl,
            type = this?.type,
            name = this?.name,
            company = this?.company,
            blog = this?.blog,
            location = this?.location,
            publicRepos = this?.publicRepos,
            followers = this?.followers,
            following = this?.following,
            bio = this?.bio,
        )
    }

    fun FavouriteItem.favouriteItemToEntity(): FavouriteEntity {
        return FavouriteEntity(
            idUser = this.idUser,
            login = this.login,
            avatarUrl = this.avatarUrl,
            type = this.type,
            name = this.name,
            company = this.company,
            blog = this.blog,
            location = this.location,
            publicRepos = this.publicRepos,
            followers = this.followers,
            following = this.following,
            bio = this.bio,
        )
    }

    fun DetailDto.popularDetailDtoToItem(): DetailItem {
        return DetailItem(
            id = this.id,
            login = this.login,
            avatarUrl = this.avatarUrl,
            type = this.type,
            name = this.name,
            company = this.company,
            blog = this.blog,
            location = this.location,
            publicRepos = this.publicRepos,
            followers = this.followers,
            following = this.following,
            bio = this.bio,
        )
    }

    fun DetailItem.popularItemToFavouriteItem(): FavouriteItem {
        return FavouriteItem(
            idUser = this.id,
            login = this.login,
            avatarUrl = this.avatarUrl,
            type = this.type,
            name = this.name,
            company = this.company,
            blog = this.blog,
            location = this.location,
            publicRepos = this.publicRepos,
            followers = this.followers,
            following = this.following,
            bio = this.bio,
        )
    }
}