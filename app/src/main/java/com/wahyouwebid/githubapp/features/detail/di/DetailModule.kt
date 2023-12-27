package com.wahyouwebid.githubapp.features.detail.di

import com.wahyouwebid.githubapp.features.detail.data.DetailApiService
import com.wahyouwebid.githubapp.features.detail.data.DetailDataRepository
import com.wahyouwebid.githubapp.features.detail.domain.DetailRepository
import com.wahyouwebid.githubapp.features.popular.data.local.PopularDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DetailModule {

    @Provides
    @Singleton
    fun provideDataRepository(
        apiService: DetailApiService,
        dao: PopularDao
    ): DetailRepository {
        return DetailDataRepository(apiService, dao)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): DetailApiService {
        return retrofit.create(DetailApiService::class.java)
    }
}