package com.example.test.common.data.network.location

import com.example.test.common.data.mapper.location.LocationMapper
import com.example.test.common.data.model.base.getResultOrThrowException
import com.example.test.common.domain.model.episode.Episode
import com.example.test.common.domain.model.location.Location
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationApi: LocationApi,
    private val locationMapper: LocationMapper
) : LocationRepository {
    override suspend fun getLocation(): List<Location> {
        val episode = locationApi.getLocation().getResultOrThrowException()
        return episode.map(locationMapper::transform)
    }

    override suspend fun getLocation(id: Int): Location {
        val episode = locationApi.getSingleLocation(id)
        return locationMapper.transform(episode)
    }

    override suspend fun getLocationIds(id: List<Int>): List<Location> {
        val episode = locationApi.getLocationIds(id)
        return episode.map(locationMapper::transform)
    }

}