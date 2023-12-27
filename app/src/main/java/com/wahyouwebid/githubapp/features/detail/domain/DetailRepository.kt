package com.wahyouwebid.githubapp.features.detail.domain

import com.wahyouwebid.githubapp.common.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by wahyouwebid on 29 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

interface DetailRepository {

    fun getPopularDetailRemote(login: String): Flow<Resource<DetailItem>>

    fun getPopularDetailLocal(login: String): Flow<Resource<DetailItem>>
}