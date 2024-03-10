package com.example.test.screens.confirm.sms.code.view.sms.retriever

object SmsParser {

    fun parseOneTimeCode(message: String, codeLength: Int): String? {
        val regex = "\\d{$codeLength}".toRegex()
        return regex.find(message)?.value
    }
}