package com.example.test.screens.confirm.sms.code.domain.usecase

import com.example.test.screens.confirm.sms.code.data.network.SentCodeRepository
import javax.inject.Inject


class SentCodeUseCaseImpl @Inject constructor(
    private val sentCodeRepository: SentCodeRepository
) : SentCodeUseCase {
    override suspend fun sentCode(code: String) {
        sentCodeRepository.sentCode(code)
    }
}