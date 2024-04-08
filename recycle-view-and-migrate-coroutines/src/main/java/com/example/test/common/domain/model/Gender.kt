package com.example.test.common.domain.model

import android.content.res.Resources

enum class Gender(val value: String) {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("unknown");

    companion object {
        fun getGender(value: String) =
            when (value) {
                FEMALE.value -> FEMALE
                MALE.value -> MALE
                GENDERLESS.value -> GENDERLESS
                UNKNOWN.value -> UNKNOWN
                else -> throw Resources.NotFoundException("Not found gender: $value")
            }
    }
}