package com.example.test.screens.confirm.sms.code.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface SentCodeApi {

    @GET("reg/otp/{code}")
    suspend fun sentCode(@Path("code") code: String)

    @GET("reg/otpresend")
    suspend fun resentCode()
}