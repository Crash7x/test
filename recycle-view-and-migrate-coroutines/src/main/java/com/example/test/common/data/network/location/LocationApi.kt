package com.example.test.common.data.network.location

import com.example.test.common.data.model.base.Response
import com.example.test.common.data.model.episode.EpisodeResponse
import com.example.test.common.data.model.location.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApi {

    @GET("location")
    suspend fun getLocation(): Response<LocationResponse>

    @GET("location/{id}")
    suspend fun getSingleLocation(@Path("id") id: Int): LocationResponse

    @GET("location/{id}")
    suspend fun getLocationIds(@Path("id") id: List<Int>): List<LocationResponse>
}