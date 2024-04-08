package com.example.test.screens.confirm.sms.code.domain.usecase

interface SentCodeUseCase {
    suspend fun sentCode(code: String)
}