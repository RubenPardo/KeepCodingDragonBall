package com.example.keepcodingdragonball.data.datasources.remote.model

import com.squareup.moshi.Json

data class LocationDto (
    @Json(name = "id") val id:String?,
    @Json(name = "dateShow") val dateShow:String?,
    @Json(name = "longitud") val longitude:String?,
    @Json(name = "latitud") val latitude:String?,
    @Json(name = "hero") val hero:HeroIdDto?
)

data class HeroIdDto(
    @Json(name = "id") val id:String?
)