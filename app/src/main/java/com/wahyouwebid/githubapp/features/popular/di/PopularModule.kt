package com.wahyouwebid.githubapp.features.popular.di

import androidx.paging.PagingConfig
import com.wahyouwebid.githubapp.core.database.RoomDb
import com.wahyouwebid.githubapp.features.popular.data.PopularDataRepository
import com.wahyouwebid.githubapp.features.popular.data.PopularRemoteMediator
import com.wahyouwebid.githubapp.features.popular.data.local.PopularDao
import com.wahyouwebid.githubapp.features.popular.data.remote.PopularApiService
import com.wahyouwebid.githubapp.features.popular.domain.PopularRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PopularModule {

    @Provides
    @Singleton
    fun provideDataRepository(
        remoteMediator: PopularRemoteMediator,
        pagingConfig: PagingConfig,
        dao: PopularDao
    ): PopularRepository {
        return PopularDataRepository(remoteMediator, pagingConfig, dao)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PopularApiService {
        return retrofit.create(PopularApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDao(db: RoomDb) = db.popularDao()
}