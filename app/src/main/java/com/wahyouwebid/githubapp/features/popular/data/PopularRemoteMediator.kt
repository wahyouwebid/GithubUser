package com.wahyouwebid.githubapp.features.popular.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.wahyouwebid.githubapp.core.database.RoomDb
import com.wahyouwebid.githubapp.core.mapper.Mapper.popularDtoToEntity
import com.wahyouwebid.githubapp.features.popular.data.model.PopularEntity
import com.wahyouwebid.githubapp.features.popular.data.remote.PopularApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PopularRemoteMediator @Inject constructor(
    private val roomDb: RoomDb,
    private val apiService: PopularApiService
): RemoteMediator<Int, PopularEntity>() {

    private var query: String? = null

    fun withQuery(query: String): PopularRemoteMediator {
        this.query = query
        return this
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id!! / state.config.pageSize) + 1
                    }
                }
            }

            val popular = apiService.getPopularUsers(
                query = if (query?.isNotEmpty() == true) query else "user",
                page = loadKey,
                pageSize = state.config.pageSize
            )

            roomDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    roomDb.popularDao().clearAll()
                }
                val popularEntity = popular.items.map { it.popularDtoToEntity() }
                roomDb.popularDao().upsertAll(popularEntity)
            }

            MediatorResult.Success(
                endOfPaginationReached = popular.items.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}