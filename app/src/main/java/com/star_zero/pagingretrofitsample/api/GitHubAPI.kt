package com.star_zero.pagingretrofitsample.api

import com.star_zero.pagingretrofitsample.data.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPI {
    @GET("/users/{user}/repos")
    suspend fun repos(
        @Path("user") user: String,
        @Query("page") page: Int,
        @Query("per_page") prePage: Int
    ): Response<List<Repo>>
}
