package com.example.paging.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    // http://api.stackexchange.com/2.2/answers?page=1&pagesize=30&site=stackoverflow

    @GET("answers")
    suspend fun getOwnerList(
            @Query("page") page: Int,
            @Query("pagesize") pageSize: Int,
            @Query("site") site: String
    ): Response<OwnerResponse>
}