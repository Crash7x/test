package com.example.test.common.data.mapper

import com.example.test.common.data.model.character.LocationResponse
import com.example.test.common.domain.model.Location
import com.example.test.common.utils.Mapper
import javax.inject.Inject

class LocationMapper @Inject constructor() : Mapper<LocationResponse, Location> {

    override fun transform(data: LocationResponse): Location =
        Location(
            name = data.name,
            url = data.url
        )
}