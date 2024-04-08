package com.example.test.screens.confirm.sms.code.data.network

interface SentCodeRepository {
    suspend fun sentCode(code: String)
    suspend fun resentCode()
}