package com.example.test.common.domain.model

import android.content.res.Resources

enum class Status(val value: String) {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("unknown");

    companion object {
        fun getStatus(value: String) =
            when (value) {
                ALIVE.value -> ALIVE
                DEAD.value -> DEAD
                UNKNOWN.value -> UNKNOWN
                else -> throw Resources.NotFoundException("Not found status: $value")
            }
    }
}