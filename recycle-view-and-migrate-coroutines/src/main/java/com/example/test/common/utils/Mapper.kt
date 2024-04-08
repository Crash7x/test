package com.example.test.common.utils

interface Mapper<T, R> {
    fun transform(data: T): R
}