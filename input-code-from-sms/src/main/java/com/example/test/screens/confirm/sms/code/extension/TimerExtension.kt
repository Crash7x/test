package com.example.test.screens.confirm.sms.code.extension

private const val SECONDS_IN_MINUTE = 60
private const val TIMER_PATTERN = "%01d:%02d"

fun Int.toTimer(): String {
    val minutes = this / SECONDS_IN_MINUTE
    val restSeconds = this % SECONDS_IN_MINUTE
    return TIMER_PATTERN.format(minutes, restSeconds)
}