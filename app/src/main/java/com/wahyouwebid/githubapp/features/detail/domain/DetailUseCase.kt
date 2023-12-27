package com.wahyouwebid.githubapp.features.detail.domain

import com.wahyouwebid.githubapp.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by wahyouwebid on 29 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

class DetailUseCase @Inject constructor(
    private val repository: DetailRepository
) {

    fun getPopularDetailRemote(login: String): Flow<Resource<DetailItem>> {
        return repository.getPopularDetailRemote(login)
    }

    fun getPopularDetailLocal(login: String): Flow<Resource<DetailItem>> {
        return repository.getPopularDetailLocal(login)
    }

}