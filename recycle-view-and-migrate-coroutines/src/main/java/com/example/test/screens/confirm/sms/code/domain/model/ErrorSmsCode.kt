package com.example.test.screens.confirm.sms.code.domain.model

import androidx.annotation.StringRes

//Класс сделан для дальнейшего возможного расширения вариантов ошибок
sealed class ErrorSmsCode {
    data class IncorrectSmsCode(@StringRes val descriptionErrorId: Int): ErrorSmsCode()
}