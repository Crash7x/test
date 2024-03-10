package com.example.test.screens.confirm.sms.code.domain.usecase

import kotlinx.coroutines.flow.Flow

interface TimerUseCase {
    fun flowStartTimer(time: Int = ONE_MINUTE - ONE_SECONDS): Flow<Int>
}