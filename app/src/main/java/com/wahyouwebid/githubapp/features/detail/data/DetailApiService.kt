package com.wahyouwebid.githubapp.features.detail.data

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by wahyouwebid on 01 December 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

interface DetailApiService {

    @GET("users/{login}")
    suspend fun getDetailPopularUsers(
        @Path("login") login: String?
    ): DetailDto

}