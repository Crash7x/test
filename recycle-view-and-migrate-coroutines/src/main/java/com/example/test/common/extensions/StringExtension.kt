package com.example.test.common.extensions

fun String.digits(): String = filter { char -> char.isDigit() }