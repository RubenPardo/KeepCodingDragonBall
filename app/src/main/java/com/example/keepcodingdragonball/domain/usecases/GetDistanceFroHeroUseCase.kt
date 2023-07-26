package com.example.keepcodingdragonball.domain.usecases

import com.example.keepcodingdragonball.domain.model.LocationModel
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class GetDistanceFroHeroUseCase {
    private val EARTH_RADIUS: Int = 6371

    fun invoke(loc1: LocationModel, loc2: LocationModel): Double {

        val lat1 = Math.toRadians(loc1.latitude)
        val lon1 = Math.toRadians(loc1.longitude)
        val lat2 = Math.toRadians(loc2.latitude)
        val lon2 = Math.toRadians(loc2.longitude)

        val dLat = lat2 - lat1
        val dLon = lon2 - lon1

        val a = sin(dLat / 2).pow(2) + cos(lat1) * cos(lat2) * sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return EARTH_RADIUS * c
    }
}