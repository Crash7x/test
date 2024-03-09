package com.example.test.screens.confirm.sms.code.domain.managers

import kotlinx.coroutines.flow.Flow

interface TimerManager {
    fun flowStartTimer(time: Int = ONE_MINUTE - ONE_SECONDS): Flow<Int>
}