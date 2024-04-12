package com.example.test.common.data.mapper.location

import com.example.test.common.data.model.location.LocationResponse
import com.example.test.common.domain.model.location.Location
import com.example.test.common.utils.Mapper
import javax.inject.Inject

class LocationMapper @Inject constructor() : Mapper<LocationResponse, Location> {

    override fun transform(data: LocationResponse): Location =
        Location(
            id = data.id,
            name = data.name,
            type = data.type,
            dimension = data.dimension
        )
}