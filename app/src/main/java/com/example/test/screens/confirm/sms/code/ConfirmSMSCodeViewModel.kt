package com.example.test.screens.confirm.sms.code

import androidx.lifecycle.ViewModel
import com.example.test.common.extensions.launchOrError
import com.example.test.screens.confirm.sms.code.domain.managers.TimerManager
import com.example.test.screens.confirm.sms.code.domain.model.TimerState
import com.example.test.screens.confirm.sms.code.domain.usecase.ResentCodeUseCase
import com.example.test.screens.confirm.sms.code.extension.toTimer
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion

class ConfirmSMSCodeViewModel @AssistedInject constructor(
    private val timerManager: TimerManager,
    private val resentCodeUseCase: ResentCodeUseCase
) : ViewModel() {

    private val _timerState = MutableStateFlow<TimerState>(TimerState.Start(0.toTimer()))
    val timerState = _timerState.asStateFlow()

    init {
        startTimer()
    }

    fun resentCode() {
        launchOrError(error = { startTimer() }) {
            resentCodeUseCase.resentCode()
            startTimer()
        }
    }

    private fun startTimer() {
        launchOrError {
            timerManager.flowStartTimer()
                .onCompletion {
                    _timerState.tryEmit(TimerState.Stop)
                }
                .collect {
                    _timerState.tryEmit(TimerState.Start(it.toTimer()))
                }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(): ConfirmSMSCodeViewModel
    }
}