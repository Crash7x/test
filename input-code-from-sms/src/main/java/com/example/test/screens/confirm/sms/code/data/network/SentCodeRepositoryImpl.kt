package com.example.test.screens.confirm.sms.code.data.network

import javax.inject.Inject

class SentCodeRepositoryImpl @Inject constructor(
    private val sentCodeApi: SentCodeApi
) : SentCodeRepository {

    override suspend fun sentCode(code: String) {
       sentCodeApi.sentCode(code)
    }

    override suspend fun resentCode() {
        sentCodeApi.resentCode()
    }
}