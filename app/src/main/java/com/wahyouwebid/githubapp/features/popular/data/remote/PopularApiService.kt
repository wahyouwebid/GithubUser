package com.wahyouwebid.githubapp.features.popular.data.remote

import com.wahyouwebid.githubapp.features.popular.data.model.PopularDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wahyouwebid on 29 November 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

interface PopularApiService {

    @GET("search/users?sort=followers")
    suspend fun getPopularUsers(
        @Query("q") query: String?,
        @Query("page") page: Int? = 1,
        @Query("per_page") pageSize: Int? = 10
    ): PopularDto

}