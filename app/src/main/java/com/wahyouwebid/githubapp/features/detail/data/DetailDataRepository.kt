package com.wahyouwebid.githubapp.features.detail.data

import com.wahyouwebid.githubapp.common.utils.Resource
import com.wahyouwebid.githubapp.core.mapper.Mapper.popularDetailDtoToItem
import com.wahyouwebid.githubapp.core.mapper.Mapper.popularEntityToDetailItem
import com.wahyouwebid.githubapp.features.detail.domain.DetailItem
import com.wahyouwebid.githubapp.features.detail.domain.DetailRepository
import com.wahyouwebid.githubapp.features.popular.data.local.PopularDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by wahyouwebid on 01 December 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

class DetailDataRepository @Inject constructor(
    private val apiService: DetailApiService,
    private val dao: PopularDao
): DetailRepository {

    override fun getPopularDetailRemote(login: String): Flow<Resource<DetailItem>> = flow {
        try {
            emit(Resource.Loading)
            emit(Resource.Success(apiService.getDetailPopularUsers(login).popularDetailDtoToItem()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getPopularDetailLocal(login: String): Flow<Resource<DetailItem>> = flow {
        try {
            emit(Resource.Success(dao.getById(login).popularEntityToDetailItem()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

}