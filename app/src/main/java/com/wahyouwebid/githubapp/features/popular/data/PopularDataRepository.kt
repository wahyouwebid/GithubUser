package com.wahyouwebid.githubapp.features.popular.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.wahyouwebid.githubapp.core.mapper.Mapper.popularEntityToItem
import com.wahyouwebid.githubapp.features.popular.data.local.PopularDao
import com.wahyouwebid.githubapp.features.popular.domain.PopularRepository
import com.wahyouwebid.githubapp.features.popular.domain.model.PopularItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by wahyouwebid on 29 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

@OptIn(ExperimentalPagingApi::class)
class PopularDataRepository @Inject constructor(
    private val remoteMediator: PopularRemoteMediator,
    private val pagingConfig: PagingConfig,
    private val dao: PopularDao,
): PopularRepository {

    override fun getPopular(query: String): Flow<PagingData<PopularItem>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = remoteMediator.withQuery(query),
            pagingSourceFactory = { dao.getAll() }
        ).flow.map {
            entity -> entity.map {
                it.popularEntityToItem()
            }
        }.flowOn(Dispatchers.IO)
    }

}