package com.example.test.common.data.network.location

import com.example.test.common.domain.model.location.Location

interface LocationRepository {
    suspend fun getLocation(): List<Location>

    suspend fun getLocation(id: Int): Location

    suspend fun getLocationIds(id: List<Int>): List<Location>
}