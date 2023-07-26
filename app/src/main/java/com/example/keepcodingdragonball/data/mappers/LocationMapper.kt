package com.example.keepcodingdragonball.data.mappers

import com.example.keepcodingdragonball.data.datasources.remote.model.LocationDto
import com.example.keepcodingdragonball.domain.model.LocationModel

fun LocationDto.toDomain() = LocationModel(
    latitude = latitude?.toDouble() ?: 0.0,
    longitude = longitude?.toDouble() ?: 0.0,
)