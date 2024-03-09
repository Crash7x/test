package com.example.test.screens.confirm.sms.code.domain.managers

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

const val ONE_SECONDS = 1
const val ONE_MINUTE = 60

class TimerManagerImpl @Inject constructor() : TimerManager {

    override fun flowStartTimer(time: Int): Flow<Int> = (time downTo 0)
        .asSequence()
        .asFlow()
        .onEach { delay(1000) }
        .catch { Timber.e(it) }

}