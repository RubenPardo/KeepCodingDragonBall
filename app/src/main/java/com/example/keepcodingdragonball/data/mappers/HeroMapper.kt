package com.example.keepcodingdragonball.data.mappers

import com.example.keepcodingdragonball.data.datasources.model.HeroDTO
import com.example.keepcodingdragonball.domain.model.Hero

fun HeroDTO.toDomain() = Hero(
    id = id ?: "",
    photo = photo ?: "",
    description = description ?: "",
    name = name ?: "",
    favorite = favorite ?: false,

)
