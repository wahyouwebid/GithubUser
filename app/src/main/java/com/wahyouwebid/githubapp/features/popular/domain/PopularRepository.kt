package com.wahyouwebid.githubapp.features.popular.domain

import androidx.paging.PagingData
import com.wahyouwebid.githubapp.features.popular.domain.model.PopularItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by wahyouwebid on 29 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

interface PopularRepository {

    fun getPopular(query: String): Flow<PagingData<PopularItem>>
}