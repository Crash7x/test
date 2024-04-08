package com.example.test.screens.confirm.sms.code.domain.usecase

import com.example.test.screens.confirm.sms.code.data.network.SentCodeRepository
import javax.inject.Inject

class ResentCodeUseCaseImpl @Inject constructor(
    private val sentCodeRepository: SentCodeRepository
) : ResentCodeUseCase {
    override suspend fun resentCode() {
        sentCodeRepository.resentCode()
    }
}