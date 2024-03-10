package com.example.test.screens.confirm.sms.code

import androidx.lifecycle.ViewModel
import com.example.test.R
import com.example.test.common.extensions.launchOrError
import com.example.test.common.observable.loader.LoaderFlow
import com.example.test.common.observable.toolbar.ToolbarFlow
import com.example.test.common.observable.toolbar.model.ToolbarData
import com.example.test.screens.confirm.sms.code.domain.usecase.TimerUseCase
import com.example.test.screens.confirm.sms.code.domain.model.ErrorSmsCode
import com.example.test.screens.confirm.sms.code.domain.model.TimerState
import com.example.test.screens.confirm.sms.code.domain.usecase.ResentCodeUseCase
import com.example.test.screens.confirm.sms.code.domain.usecase.SentCodeUseCase
import com.example.test.screens.confirm.sms.code.extension.toTimer
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion

class ConfirmSMSCodeViewModel @AssistedInject constructor(
    private val timerUseCase: TimerUseCase,
    private val resentCodeUseCase: ResentCodeUseCase,
    private val sentCodeUseCase: SentCodeUseCase,
    private val toolbarFlow: ToolbarFlow,
    private val loaderFlow: LoaderFlow
) : ViewModel() {

    private val _timerState = MutableStateFlow<TimerState>(TimerState.Start(0.toTimer()))
    val timerState = _timerState.asStateFlow()

    private val _errorCodeSms = MutableSharedFlow<ErrorSmsCode>(0, 1, BufferOverflow.DROP_OLDEST)
    val errorCodeSms = _errorCodeSms.asSharedFlow()

    init {
        startTimer()
    }

    fun setupToolbar(title: String) {
        toolbarFlow.tryEmit(ToolbarData(isVisible = true, hasBackNavigation = true, title = title))
    }

    fun sentCode(code: String) {
        launchOrError(error = {
            _errorCodeSms.tryEmit(ErrorSmsCode.IncorrectSmsCode(R.string.error_sms_code))
            loaderFlow.tryEmit(false)
        }) {
            loaderFlow.tryEmit(true)
            //Добавлен для демострации показа лоадера так как ошибка падает мнгновенно
            //и лоадер не успевает появляться
            delay(5000)
            sentCodeUseCase.sentCode(code)
            loaderFlow.tryEmit(false)
        }
    }

    //В ошибку добавлен startTimer() потому что нет тз как действуем в этой ситуации
    //и для демонстрации работы так как домена нет и ошибка падает постоянно
    fun resentCode() {
        launchOrError(error = { startTimer() }) {
            resentCodeUseCase.resentCode()
            startTimer()
        }
    }

    private fun startTimer() {
        launchOrError {
            timerUseCase.flowStartTimer()
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