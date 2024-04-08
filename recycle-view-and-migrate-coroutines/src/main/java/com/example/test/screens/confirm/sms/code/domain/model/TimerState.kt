package com.example.test.screens.confirm.sms.code.domain.model

sealed class TimerState {
    data class Start(val time: String): TimerState()
    data object Stop: TimerState()
}