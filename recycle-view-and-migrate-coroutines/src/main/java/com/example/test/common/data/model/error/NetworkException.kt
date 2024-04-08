package com.example.test.common.data.model.error

class NetworkException(error: String) : Exception("server error: $error")

class UnknownException : Exception("Ошибка при получение данных из response")