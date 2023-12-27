package com.wahyouwebid.githubapp.features.favourite.di

import androidx.paging.PagingConfig
import com.wahyouwebid.githubapp.core.database.RoomDb
import com.wahyouwebid.githubapp.features.favourite.data.FavouriteDao
import com.wahyouwebid.githubapp.features.favourite.data.FavouriteDataRepository
import com.wahyouwebid.githubapp.features.favourite.domain.FavouriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavouriteModule {

    @Provides
    @Singleton
    fun provideDataRepository(
        pagingConfig: PagingConfig,
        dao: FavouriteDao,
    ): FavouriteRepository {
        return FavouriteDataRepository(pagingConfig, dao)
    }

    @Singleton
    @Provides
    fun provideDao(db: RoomDb) = db.favouriteDao()
}